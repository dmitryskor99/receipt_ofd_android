package ru.dmitryskor.receipt_ofd_android.ui.login.token

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

interface LoginWithTokenComponent

@AssistedFactory
interface LoginWithTokenComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext,
    ): DefaultLoginWithTokenComponent
}