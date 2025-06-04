package ru.dmitryskor.impl

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface ScannerComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext,
        userToken: String
    ): DefaultScannerComponent
}