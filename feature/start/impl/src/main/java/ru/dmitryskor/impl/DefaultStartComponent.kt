package ru.dmitryskor.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.dmitryskor.api.StartComponent
import ru.dmitryskor.api.StartState

class DefaultStartComponent @AssistedInject constructor(
    @Assisted componentContext: ComponentContext,
    @Assisted private val onLogin: () -> Unit,
) : StartComponent, ComponentContext by componentContext {
    private val _state: MutableValue<StartState> = MutableValue(StartState(isLoading = true))
    private val scope = coroutineScope()

    override val state: Value<StartState> = _state

    init {
        scope.launch(Dispatchers.IO) {
            val serverAvailable = checkServer()
            val authAvailable = checkAuth()
            when {
                serverAvailable && authAvailable -> toLogin()
                serverAvailable -> {}
                authAvailable -> {}
            }
        }
    }

    private suspend fun toLogin() = withContext(Dispatchers.Main) {
        onLogin()
    }

    private fun toApp() {
        TODO("Not yet implemented")
    }

    private suspend fun checkServer(): Boolean {
        delay(2000L)
        return true
    }

    private suspend fun checkAuth(): Boolean {
        delay(100L)
        return true
    }
}