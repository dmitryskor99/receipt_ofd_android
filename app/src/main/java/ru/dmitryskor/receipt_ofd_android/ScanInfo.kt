package ru.dmitryskor.receipt_ofd_android

import kotlinx.serialization.Serializable

@Serializable
data class ScanInfo(
    val createdDate: String? = null,
    val fiscalDocumentNumber: String? = null,
    val fiscalDriveNumber: String? = null,
    val fiscalSign: String? = null,
    val operationType: String? = null,
    val scanDate: String? = null,
    val totalSum: String? = null,
)

val EMPTY: ScanInfo = ScanInfo()