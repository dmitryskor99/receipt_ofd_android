package ru.dmitryskor.receipt_ofd_android.store

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalPrimitiveStore(
    private val dataStore: DataStore<Preferences>
) : PrimitiveStore {

    /**
     * Bearer токен с сатйта https://mco.nalog.ru
     * */
    private val privateToken = stringPreferencesKey("private_token_key")

    override fun getPrivateToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[privateToken]
        }
    }

    override suspend fun setPrivateToken(token: String) {
        dataStore.edit { preferences ->
            preferences[privateToken] = token
        }
    }
}