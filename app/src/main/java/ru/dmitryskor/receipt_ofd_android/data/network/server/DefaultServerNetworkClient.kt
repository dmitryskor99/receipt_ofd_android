package ru.dmitryskor.receipt_ofd_android.data.network.server

import io.ktor.http.isSuccess
import ru.dmitryskor.receipt_ofd_android.data.network.NetworkClient
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope
import ru.dmitryskor.receipt_ofd_android.domain.models.exception.ServerNotAvailable
import javax.inject.Inject

@AppScope
class DefaultServerNetworkClient @Inject constructor(
    private val client: NetworkClient
) : ServerNetworkClient {
    override suspend fun pingPong(): Boolean {
        val responseStatus = client.get("/ping").status
        if (!responseStatus.isSuccess()) {
            throw ServerNotAvailable(responseStatus.toString())
        }
        return true
    }
}