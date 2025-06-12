package ru.dmitryskor.receipt_ofd_android.ui.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.dmitryskor.receipt_ofd_android.ui.screen.login.LoginWithTokenComponent
import ru.dmitryskor.receipt_ofd_android.ui.screen.login.LoginWithTokenComponentFactory
import ru.dmitryskor.receipt_ofd_android.ui.screen.scanner.ScannerComponent
import ru.dmitryskor.receipt_ofd_android.ui.screen.scanner.ScannerComponentFactory
import ru.dmitryskor.receipt_ofd_android.ui.screen.start.StartComponent
import ru.dmitryskor.receipt_ofd_android.ui.screen.start.StartComponentFactory

class DefaultRootComponent @AssistedInject constructor(
    private val loginWithTokenFactory: LoginWithTokenComponentFactory,
    private val scannerFactory: ScannerComponentFactory,
    private val startFactory: StartComponentFactory,
    @Assisted componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val nav = StackNavigation<Config>()
    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = nav,
            initialConfiguration = Config.Start,
            serializer = Config.serializer(),
            handleBackButton = true,
            childFactory = ::child,
        )

    private fun child(config: Config, context: ComponentContext): RootComponent.Child {
        return when (config) {
            is Config.Start -> RootComponent.Child.Start(startComponent(context))
            is Config.LoginWithToken -> RootComponent.Child.LoginWithToken(loginWithTokenComponent(context))
            is Config.Scanner -> RootComponent.Child.Scanner(scannerComponent(context, config.userToken))
        }
    }

    private fun loginWithTokenComponent(context: ComponentContext): LoginWithTokenComponent {
        return loginWithTokenFactory(
            componentContext = context,
            onLogin = {
                nav.pushNew(Config.Scanner(it))
            }
        )
    }

    private fun scannerComponent(context: ComponentContext, userToken: String): ScannerComponent {
        return scannerFactory(
            componentContext = context,
            userToken = userToken
        )
    }

    private fun startComponent(context: ComponentContext): StartComponent {
        return startFactory(
            componentContext = context,
            onLogin = {
                nav.pushNew(Config.LoginWithToken)
            }
        )
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Start : Config

        @Serializable
        data object LoginWithToken : Config

        @Serializable
        data class Scanner(val userToken: String) : Config
    }
}