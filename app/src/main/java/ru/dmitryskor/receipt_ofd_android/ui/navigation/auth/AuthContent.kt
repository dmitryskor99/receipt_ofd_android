package ru.dmitryskor.receipt_ofd_android.ui.navigation.auth

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.dmitryskor.receipt_ofd_android.ui.screens.ScanScreen

@Composable
fun AuthContent(root: AuthRootComponent) {
    Children(
        stack = root.childStack
    ) { child ->
        when (val instance = child.instance) {
            is AuthRootComponent.Child.ScanScreen -> ScanScreen(component = instance.component)
        }
    }
}