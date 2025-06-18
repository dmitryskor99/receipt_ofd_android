package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

import com.arkivanov.decompose.value.Value

interface ScannerComponent {
    val state: Value<ScannerState>

    fun onScanned(scan: String)
    fun onClickReload()
}