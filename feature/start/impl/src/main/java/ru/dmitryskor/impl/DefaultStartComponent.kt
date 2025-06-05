package ru.dmitryskor.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.dmitryskor.api.StartComponent
import ru.dmitryskor.api.StartState

class DefaultStartComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext
) : StartComponent, ComponentContext by componentContext {
    private val _state: MutableValue<StartState> = MutableValue(StartState(isLoading = true))

    override val state: Value<StartState> = _state

    override fun toLogin() {
        TODO("Not yet implemented")
    }

    override fun toApp() {
        TODO("Not yet implemented")
    }
}