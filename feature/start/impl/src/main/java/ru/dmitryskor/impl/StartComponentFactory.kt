package ru.dmitryskor.impl

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface StartComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext,
        onLogin: () -> Unit,
    ): DefaultStartComponent
}