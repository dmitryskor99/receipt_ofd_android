package ru.dmitryskor.receipt_ofd_android.ui.navigation.noauth

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.dmitryskor.receipt_ofd_android.ui.screens.AuthPrivateTokenScreen

@Composable
fun NoAuthContent(root: NoAuthRootComponent) {
    Children(
        stack = root.childStack
    ) { child ->
        when (val instance = child.instance) {
            is NoAuthRootComponent.Child.AuthPrivateToken -> AuthPrivateTokenScreen(component = instance.component)
        }
    }
}