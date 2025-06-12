package ru.dmitryskor.receipt_ofd_android.ui.screen.start

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.dmitryskor.api.ServerNetworkClient

class DefaultStartComponent @AssistedInject constructor(
    private val serverNetworkClient: ServerNetworkClient,
    @Assisted componentContext: ComponentContext,
    @Assisted private val onLogin: () -> Unit,
) : StartComponent, ComponentContext by componentContext {
    private val _state: MutableValue<StartState> = MutableValue(StartState.Loading)
    private val scope = coroutineScope()

    override val state: Value<StartState> = _state

    override fun onReloadCheckServer() {
        initStartFlow()
    }

    init {
        initStartFlow()
    }

    private fun initStartFlow() {
        scope.launch(Dispatchers.Default) {
            val serverAvailableDeffered = scope.async(Dispatchers.IO) {
                checkServer()
            }
            val authAvailableDeffered = scope.async(Dispatchers.IO) {
                checkAuth()
            }

            val serverAvailable = serverAvailableDeffered.await()
            val authAvailable = authAvailableDeffered.await()

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
        return serverNetworkClient.pingPong().onFailure {
            it
            _state.update { StartState.ServerNotAvailable }
        }.isSuccess
    }

    private suspend fun checkAuth(): Boolean {
        delay(100L)
        return true
    }
}