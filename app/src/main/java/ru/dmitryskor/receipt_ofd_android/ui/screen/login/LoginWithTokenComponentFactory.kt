package ru.dmitryskor.receipt_ofd_android.ui.screen.login

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface LoginWithTokenComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext,
        onLogin: () -> Unit
    ): DefaultLoginWithTokenComponent
}