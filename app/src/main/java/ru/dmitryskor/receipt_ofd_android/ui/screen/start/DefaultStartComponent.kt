package ru.dmitryskor.receipt_ofd_android.ui.screen.start

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.dmitryskor.receipt_ofd_android.domain.CheckAvailableServerUC
import ru.dmitryskor.receipt_ofd_android.domain.GetTokenUC
import ru.dmitryskor.receipt_ofd_android.domain.models.exception.ServerNotAvailable

class DefaultStartComponent @AssistedInject constructor(
    private val checkAvailableServer: CheckAvailableServerUC,
    private val checkLogin: GetTokenUC,
    @Assisted componentContext: ComponentContext,
    @Assisted private val onLogin: () -> Unit,
    @Assisted private val onApp: (String) -> Unit,
) : StartComponent, ComponentContext by componentContext {
    private val _state: MutableValue<StartState> = MutableValue(StartState.Loading)
    private val scope = coroutineScope(
        CoroutineName("DefaultStartComponent") + CoroutineExceptionHandler { _, t ->
            when (t) {
                is ServerNotAvailable -> _state.update { StartState.ServerNotAvailable(error = t.message.orEmpty()) }
                else -> _state.update { StartState.UnknownError(error = t.message.orEmpty()) }
            }
        }
    )

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
            val authCredentialsDeffered = scope.async(Dispatchers.IO) {
                checkAuth()
            }

            val serverAvailable = serverAvailableDeffered.await()
            val authCredentials = authCredentialsDeffered.await()

            when {
                serverAvailable && authCredentials != null -> toApp(authCredentials)
                serverAvailable -> toLogin()
                else -> {
                    // Noop: Сервер недоступен, но мы авторизованы. Просто ждём, пока юзер сервер будет доступен
                }
            }
        }
    }

    private suspend fun toLogin() = withContext(Dispatchers.Main) {
        onLogin()
    }

    private suspend fun toApp(token: String) = withContext(Dispatchers.Main) {
        onApp(token)
    }

    private suspend fun checkServer(): Boolean = checkAvailableServer()

    private suspend fun checkAuth(): String? = checkLogin()
}