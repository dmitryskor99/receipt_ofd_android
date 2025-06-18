package ru.dmitryskor.receipt_ofd_android.data.network.scan

import io.ktor.http.isSuccess
import ru.dmitryskor.receipt_ofd_android.data.network.NetworkClient

class DefaultScanNetworkClient(
    private val client: NetworkClient
) : ScanNetworkClient {
    override suspend fun sendScan(scan: String): Result<Unit> = runCatching {
        val responseStatus = client.get("/scan", mapOf("qr" to scan)).status
        if (responseStatus.isSuccess()) {
            Unit
        } else {
            throw Exception(responseStatus.toString())
        }
    }
}