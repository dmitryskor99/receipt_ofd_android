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

class DefaultRootComponent @AssistedInject constructor(
    private val loginWithTokenFactory: LoginWithTokenComponentFactory,
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

    private fun child(config: Config, context: ComponentContext): RootComponent.Child =
        when (config) {
            is Config.LoginWithToken -> RootComponent.Child.LoginWithToken(loginWithTokenComponent(context))
        }

    private fun loginWithTokenComponent(context: ComponentContext): LoginWithTokenComponent =
        loginWithTokenFactory(
            componentContext = context
        )

    @Serializable
    private sealed interface Config {
        @Serializable
        data object LoginWithToken : Config
    }
}