package ru.dmitryskor.receipt_ofd_android.ui.navigation

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceAll
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.dmitryskor.receipt_ofd_android.ScanInfo
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.QrResultComponent
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.QrScannerComponent
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.QrSendDataComponent
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.QrSendResultComponent

class RootComponent(
    componentContext: ComponentContext,
    private val applicationContext: Context
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val childStack: Value<com.arkivanov.decompose.router.stack.ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Config.QrScanner,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(DelicateDecomposeApi::class)
    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            is Config.QrScanner -> Child.QrScanner(
                QrScannerComponent(
                    componentContext = componentContext,
                    onQrScanned = { result ->
                        navigation.push(Config.QrResult(result))
                    }
                )
            )
            is Config.QrResult -> Child.QrResult(
                QrResultComponent(
                    componentContext = componentContext,
                    result = config.result,
                    token = applicationContext.getToken(),
                    onBack = { navigation.pop() },
                    onSend = { data, token ->
                        navigation.push(Config.QrSendData(data, token))
                        applicationContext.setToken(token)
                    }
                )
            )

            is Config.QrSendData -> Child.QrSendData(
                QrSendDataComponent(
                    componentContext = componentContext,
                    data = config.data,
                    token = config.token,
                    onShowResult = {
                        navigation.push(Config.QrSendResult(it))
                    }
                )
            )

            is Config.QrSendResult -> Child.QrSendResult(
                QrSendResultComponent(
                    componentContext = componentContext,
                    response = config.response,
                    onClearStack = {
                        navigation.replaceAll(Config.QrScanner)
                    }
                )
            )
        }
    }

    sealed class Child {
        data class QrScanner(val component: QrScannerComponent) : Child()
        data class QrResult(val component: QrResultComponent) : Child()
        data class QrSendData(val component: QrSendDataComponent) : Child()
        data class QrSendResult(val component: QrSendResultComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object QrScanner : Config()

        @Serializable
        data class QrResult(val result: String) : Config()

        @Serializable
        data class QrSendData(val data: ScanInfo, val token: String) : Config()

        @Serializable
        data class QrSendResult(val response: String) : Config()
    }
}

private fun Context.getToken(): String {
    val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    return prefs.getString("auth_token", "") ?: ""
}

private fun Context.setToken(token: String) {
    val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    prefs.edit().putString("auth_token", token).apply()
}
