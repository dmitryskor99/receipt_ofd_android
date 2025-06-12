package ru.dmitryskor.receipt_ofd_android.ui.navigation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.dmitryskor.receipt_ofd_android.ui.screen.login.LoginWithTokenComponent
import ru.dmitryskor.receipt_ofd_android.ui.screen.scanner.ScannerComponent
import ru.dmitryskor.receipt_ofd_android.ui.screen.start.StartComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed class Child {
        class Start(val component: StartComponent) : Child()
        class LoginWithToken(val component: LoginWithTokenComponent) : Child()
        class Scanner(val component: ScannerComponent) : Child()
    }
}