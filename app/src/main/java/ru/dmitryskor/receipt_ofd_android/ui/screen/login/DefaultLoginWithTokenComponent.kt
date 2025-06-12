package ru.dmitryskor.receipt_ofd_android.ui.screen.login

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class DefaultLoginWithTokenComponent @AssistedInject constructor(
    context: Context,
    @Assisted componentContext: ComponentContext,
    @Assisted private val onLogin: (String) -> Unit
) : LoginWithTokenComponent, ComponentContext by componentContext {
    private val _state: MutableValue<LoginWithTokenState> = MutableValue(LoginWithTokenState("е"))

    override val state: Value<LoginWithTokenState> = _state

    override fun onTokenInputChange(newTokenInput: String) {
        _state.update { it.copy(tokenInput = newTokenInput) }
    }

    override fun onNext() {
        onLogin(state.value.tokenInput)
    }
}