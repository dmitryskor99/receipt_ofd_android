package ru.dmitryskor.receipt_ofd_android.data.network.scan

interface ScanNetworkClient {
    suspend fun sendScan(scan: String): Result<Unit>
}