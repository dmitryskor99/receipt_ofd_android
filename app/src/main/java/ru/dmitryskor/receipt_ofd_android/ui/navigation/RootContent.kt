package ru.dmitryskor.receipt_ofd_android.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.dmitryskor.receipt_ofd_android.ui.navigation.auth.AuthContent
import ru.dmitryskor.receipt_ofd_android.ui.navigation.noauth.NoAuthContent

@Composable
fun RootContent(root: RootComponent) {
    Children(stack = root.childStack) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.NoAuth -> NoAuthContent(root = instance.component)
            is RootComponent.Child.Auth -> AuthContent(root = instance.component)
        }
    }
}
