package ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.firstOrNull
import ru.dmitryskor.receipt_ofd_android.domain.models.UserCredentials
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class EncryptionPrimitivesDataStore(
    private val context: Context
) : PrimitivesDataStore {

    override suspend fun setUserToken(userCredentials: UserCredentials) {
        context.encryptStore.updateData { userCredentials }
    }

    override suspend fun getUserToken(): UserCredentials {
        return context.encryptStore.data.firstOrNull() ?: UserCredentials(token = null)
    }


    companion object {
        private const val KEY_ALIAS = "primitives_store"
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

        private val cipher = Cipher.getInstance(TRANSFORMATION)

        private val keyStore = KeyStore
            .getInstance("AndroidKeyStore")
            .apply {
                load(null)
            }

        private val Context.encryptStore by dataStore<UserCredentials>(
            fileName = "primitives_store",
            serializer = UserCredentialsSerializer(
                encrypt = ::encrypt,
                decrypt = ::decrypt
            )
        )

        private fun getKey(): SecretKey {
            val existingKey = keyStore
                .getEntry(KEY_ALIAS, null) as? KeyStore.SecretKeyEntry
            return existingKey?.secretKey ?: createKey()
        }

        private fun createKey(): SecretKey {
            return KeyGenerator
                .getInstance(ALGORITHM)
                .apply {
                    init(
                        KeyGenParameterSpec.Builder(
                            KEY_ALIAS,
                            KeyProperties.PURPOSE_ENCRYPT or
                                    KeyProperties.PURPOSE_DECRYPT
                        )
                            .setBlockModes(BLOCK_MODE)
                            .setEncryptionPaddings(PADDING)
                            .setRandomizedEncryptionRequired(true)
                            .setUserAuthenticationRequired(false)
                            .build()
                    )
                }
                .generateKey()
        }

        private fun encrypt(byteArray: ByteArray): ByteArray {
            cipher.init(Cipher.ENCRYPT_MODE, getKey())
            val iv = cipher.iv
            val encrypted = cipher.doFinal(byteArray)
            return iv + encrypted
        }

        private fun decrypt(byteArray: ByteArray): ByteArray {
            val iv = byteArray.copyOfRange(0, cipher.blockSize)
            val data = byteArray.copyOfRange(cipher.blockSize, byteArray.size)
            cipher.init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
            return cipher.doFinal(data)
        }
    }
}