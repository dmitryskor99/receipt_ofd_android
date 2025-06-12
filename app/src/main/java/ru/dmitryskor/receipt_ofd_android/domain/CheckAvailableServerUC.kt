package ru.dmitryskor.receipt_ofd_android.domain

import ru.dmitryskor.receipt_ofd_android.data.network.server.ServerNetworkClient
import javax.inject.Inject

class CheckAvailableServerUC @Inject constructor(
    private val serverNetworkClient: ServerNetworkClient
) {
    suspend operator fun invoke(): Boolean {
        return serverNetworkClient.pingPong()
    }
}