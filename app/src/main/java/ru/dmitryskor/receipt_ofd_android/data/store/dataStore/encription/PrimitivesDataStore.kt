package ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription

import ru.dmitryskor.receipt_ofd_android.domain.models.UserCredentials

interface PrimitivesDataStore {
    suspend fun setUserToken(userCredentials: UserCredentials)
    suspend fun getUserToken(): UserCredentials
}