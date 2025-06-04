package ru.dmitryskor.impl

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.dmitryskor.api.LoginWithTokenComponent
import ru.dmitryskor.api.RootComponent
import ru.dmitryskor.api.ScannerComponent

class DefaultRootComponent @AssistedInject constructor(
    private val loginWithTokenFactory: LoginWithTokenComponentFactory,
    private val scannerFactory: ScannerComponentFactory,
    @Assisted componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = nav,
            initialConfiguration = Config.LoginWithToken,
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, context: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config.LoginWithToken -> RootComponent.Child.LoginWithToken(loginWithTokenComponent(context))
            is Config.Scanner -> RootComponent.Child.Scanner(scannerComponent(context, config.userToken))
        }
    }

    private fun loginWithTokenComponent(context: ComponentContext): LoginWithTokenComponent {
        return loginWithTokenFactory(
            componentContext = context,
            onLogin = {

            }
        )
    }

    private fun scannerComponent(context: ComponentContext, userToken: String): ScannerComponent {
        return scannerFactory(
            componentContext = context,
            userToken = userToken
        )
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object LoginWithToken : Config

        @Serializable
        data class Scanner(val userToken: String) : Config
    }
}