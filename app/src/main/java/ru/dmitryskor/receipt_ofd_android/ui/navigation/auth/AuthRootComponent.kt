package ru.dmitryskor.receipt_ofd_android.ui.navigation.auth

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.ScanComponent

class AuthRootComponent(
    componentContext: ComponentContext,
    val onLogout: () -> Unit
) : ComponentContext by componentContext {
    private val navigation = StackNavigation<Config>()

    val childStack: Value<ChildStack<Config, Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Config.ScanScreen,
        handleBackButton = true,
        childFactory = ::createChild
    )

    private fun createChild(config: Config, componentContext: ComponentContext): Child {
        return when (config) {
            is Config.ScanScreen -> Child.ScanScreen(
                component = ScanComponent(
                    componentContext = componentContext,
                    onScanned = ::onScanned
                )
            )
        }
    }

    private fun onScanned(type: ScanComponent.ScanType) {

    }

    sealed class Child {
        data class ScanScreen(val component: ScanComponent) : Child()
    }

    @Serializable
    sealed class Config {
        @Serializable
        data object ScanScreen : Config()
    }
}