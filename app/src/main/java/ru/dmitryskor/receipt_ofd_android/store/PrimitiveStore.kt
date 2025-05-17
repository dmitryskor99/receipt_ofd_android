package ru.dmitryskor.receipt_ofd_android.store

import kotlinx.coroutines.flow.Flow

interface PrimitiveStore {
    fun getPrivateToken(): Flow<String?>

    suspend fun setPrivateToken(token: String)
}