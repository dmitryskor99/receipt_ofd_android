package ru.dmitryskor.receipt_ofd_android.store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "primitives_store")

class LocalPrimitiveStore(
    private val context: Context
) {

    /**
     * Bearer токен с сатйта https://mco.nalog.ru
     * */
    private val privateToken = stringPreferencesKey("private_token_key")

    fun getPrivateToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[privateToken]
        }
    }

    suspend fun setPrivateToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[privateToken] = token
        }
    }
}