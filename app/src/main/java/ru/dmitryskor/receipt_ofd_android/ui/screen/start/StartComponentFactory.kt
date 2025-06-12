package ru.dmitryskor.receipt_ofd_android.ui.screen.start

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface StartComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext,
        onLogin: () -> Unit,
        onApp: (String) -> Unit,
    ): DefaultStartComponent
}