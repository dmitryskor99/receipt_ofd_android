package ru.dmitryskor.receipt_ofd_android.ui.navigation.noauth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.AuthPrivateTokenComponent

class NoAuthRootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    val childStack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Config.AuthPrivateToken,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            is Config.AuthPrivateToken -> Child.AuthPrivateToken(
                component = AuthPrivateTokenComponent(
                    componentContext = componentContext
                )
            )
        }
    }

    sealed class Child {
        data class AuthPrivateToken(val component: AuthPrivateTokenComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object AuthPrivateToken : Config()
    }
}
