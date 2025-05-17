package ru.dmitryskor.receipt_ofd_android.ui.navigation

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.dmitryskor.receipt_ofd_android.ui.navigation.auth.AuthRootComponent
import ru.dmitryskor.receipt_ofd_android.ui.navigation.noauth.NoAuthRootComponent

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    val childStack: Value<com.arkivanov.decompose.router.stack.ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Config.NoAuth,
        handleBackButton = false,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            is Config.NoAuth -> Child.NoAuth(
                component = NoAuthRootComponent(
                    componentContext = componentContext
                )
            )

            is Config.Auth -> Child.Auth(
                component = AuthRootComponent(
                    componentContext = componentContext,
                    onLogout = {
                        // TODO
                    }
                )
            )
        }
    }

    sealed class Child {
        data class NoAuth(val component: NoAuthRootComponent) : Child()
        data class Auth(val component: AuthRootComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object NoAuth : Config()

        @Serializable
        data class Auth(val token: String) : Config()
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
