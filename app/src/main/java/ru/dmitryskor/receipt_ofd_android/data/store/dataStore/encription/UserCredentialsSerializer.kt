package ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription

import androidx.datastore.core.Serializer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import ru.dmitryskor.receipt_ofd_android.domain.models.UserCredentials
import java.io.InputStream
import java.io.OutputStream
import kotlin.io.encoding.ExperimentalEncodingApi

class UserCredentialsSerializer(
    private val encrypt: (ByteArray) -> ByteArray,
    private val decrypt: (ByteArray) -> ByteArray
) : Serializer<UserCredentials> {
    override val defaultValue: UserCredentials
        get() = UserCredentials(token = null)

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun readFrom(input: InputStream): UserCredentials {
        val encryptedBytes = withContext(Dispatchers.IO) {
            input.use { it.readBytes() }
        }
        val decryptedBytes = decrypt(encryptedBytes)
        val decodedJsonString = decryptedBytes.decodeToString()
        return Json.decodeFromString(decodedJsonString)
    }

    @OptIn(ExperimentalEncodingApi::class)
    override suspend fun writeTo(t: UserCredentials, output: OutputStream) {
        val json = Json.encodeToString(t)
        val bytes = json.toByteArray()
        val encryptedBytes = encrypt(bytes)
        withContext(Dispatchers.IO) {
            output.use {
                it.write(encryptedBytes)
            }
        }
    }
}