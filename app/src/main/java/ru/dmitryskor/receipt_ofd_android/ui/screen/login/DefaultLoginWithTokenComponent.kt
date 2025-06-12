package ru.dmitryskor.receipt_ofd_android.ui.screen.login

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.dmitryskor.receipt_ofd_android.domain.SetTokenUC

class DefaultLoginWithTokenComponent @AssistedInject constructor(
    private val setToken: SetTokenUC,
    @Assisted componentContext: ComponentContext,
    @Assisted private val onLogin: () -> Unit
) : LoginWithTokenComponent, ComponentContext by componentContext {
    private val _state: MutableValue<LoginWithTokenState> = MutableValue(LoginWithTokenState(tokenInput = ""))
    private val scope = coroutineScope(CoroutineName("DefaultLoginWithTokenComponent"))

    override val state: Value<LoginWithTokenState> = _state

    override fun onTokenInputChange(newTokenInput: String) {
        _state.update { it.copy(tokenInput = newTokenInput) }
    }

    override fun onNext() {
        scope.launch(Dispatchers.IO) {
            setToken.invoke(_state.value.tokenInput)
            withContext(Dispatchers.Main) {
                onLogin()
            }
        }
    }
}