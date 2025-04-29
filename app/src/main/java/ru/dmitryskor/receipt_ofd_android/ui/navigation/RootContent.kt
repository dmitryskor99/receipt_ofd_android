package ru.dmitryskor.receipt_ofd_android.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.dmitryskor.receipt_ofd_android.ui.screens.QrResultScreen
import ru.dmitryskor.receipt_ofd_android.ui.screens.QrScannerScreen
import ru.dmitryskor.receipt_ofd_android.ui.screens.QrSendDataScreen
import ru.dmitryskor.receipt_ofd_android.ui.screens.QrSendResultScreen

@Composable
fun RootContent(root: RootComponent) {
    Children(stack = root.childStack) { child ->
        when (val instance = child.instance) {
            is RootComponent.Child.QrScanner -> QrScannerScreen(instance.component)
            is RootComponent.Child.QrResult -> QrResultScreen(instance.component)
            is RootComponent.Child.QrSendData -> QrSendDataScreen(instance.component)
            is RootComponent.Child.QrSendResult -> QrSendResultScreen(instance.component)
        }
    }
}
