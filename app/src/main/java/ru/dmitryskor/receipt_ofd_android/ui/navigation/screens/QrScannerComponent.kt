package ru.dmitryskor.receipt_ofd_android.ui.navigation.screens

import com.arkivanov.decompose.ComponentContext

class QrScannerComponent(
    componentContext: ComponentContext,
    val onQrScanned: (String) -> Unit
) : ComponentContext by componentContext

