package ru.dmitryskor.receipt_ofd_android.ui.navigation

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface RootComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext
    ): DefaultRootComponent
}