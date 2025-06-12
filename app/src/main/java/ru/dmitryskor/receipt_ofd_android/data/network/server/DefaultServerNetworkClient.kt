package ru.dmitryskor.receipt_ofd_android.data.network.server

import io.ktor.http.isSuccess
import ru.dmitryskor.receipt_ofd_android.data.network.NetworkClient
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope
import javax.inject.Inject

@AppScope
class DefaultServerNetworkClient @Inject constructor(
    private val client: NetworkClient
) : ServerNetworkClient {
    override suspend fun pingPong(): Result<Unit> {
        val response = client.get("/ping").status
        return if (response.isSuccess()) {
            Result.success(Unit)
        } else {
            Result.failure(Exception())
        }
    }
}