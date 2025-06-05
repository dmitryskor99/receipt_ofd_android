package ru.dmitryskor.api

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Start(val component: StartComponent) : Child()
        class LoginWithToken(val component: LoginWithTokenComponent) : Child()
        class Scanner(val component: ScannerComponent) : Child()
    }
}