package ru.dmitryskor.impl

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.AssistedFactory

@AssistedFactory
interface RootComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext
    ): DefaultRootComponent
}