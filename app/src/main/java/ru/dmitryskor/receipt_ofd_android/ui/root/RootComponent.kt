package ru.dmitryskor.receipt_ofd_android.ui.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.AssistedFactory
import ru.dmitryskor.receipt_ofd_android.ui.login.token.LoginWithTokenComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class LoginWithToken(val component: LoginWithTokenComponent) : Child()
    }
}

@AssistedFactory
interface RootComponentFactory {
    operator fun invoke(
        componentContext: ComponentContext
    ): DefaultRootComponent
}