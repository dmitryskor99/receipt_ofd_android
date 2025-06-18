package ru.dmitryskor.receipt_ofd_android.domain

import ru.dmitryskor.receipt_ofd_android.data.network.scan.ScanNetworkClient
import javax.inject.Inject

class SendScanUC @Inject constructor(
    private val scanNetworkClient: ScanNetworkClient
) {
    suspend operator fun invoke(scan: String): Result<Unit> {
        return scanNetworkClient.sendScan(scan = scan)
    }
}