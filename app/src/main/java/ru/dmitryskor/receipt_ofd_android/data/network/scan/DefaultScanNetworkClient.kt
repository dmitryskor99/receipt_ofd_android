package ru.dmitryskor.receipt_ofd_android.data.network.scan

import ru.dmitryskor.receipt_ofd_android.data.network.NetworkClient

class DefaultScanNetworkClient(
    private val client: NetworkClient
) : ScanNetworkClient {
    override suspend fun sendScan(scan: String): Result<Unit> = runCatching {
        client.get("/scan", mapOf("qr" to scan))
    }
}