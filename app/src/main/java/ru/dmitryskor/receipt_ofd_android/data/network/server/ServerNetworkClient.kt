package ru.dmitryskor.receipt_ofd_android.data.network.server

interface ServerNetworkClient {
    suspend fun pingPong(): Result<Unit>
}