package ru.dmitryskor.receipt_ofd_android.ui.navigation.screens

import com.arkivanov.decompose.ComponentContext

class ScanComponent(
    componentContext: ComponentContext,
    val onScanned: (ScanType) -> Unit
) {
    sealed class ScanType {
        data class StringType(val value: String) : ScanType()
    }
}