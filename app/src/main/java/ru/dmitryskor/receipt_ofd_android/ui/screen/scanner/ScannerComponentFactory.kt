package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ScannerComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext,
        userToken: String
    ): DefaultScannerComponent
}