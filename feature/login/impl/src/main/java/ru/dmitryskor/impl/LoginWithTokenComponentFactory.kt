package ru.dmitryskor.impl

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface LoginWithTokenComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext,
    ): DefaultLoginWithTokenComponent
}