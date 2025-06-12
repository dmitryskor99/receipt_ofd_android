package ru.dmitryskor.receipt_ofd_android.ui.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.dmitryskor.receipt_ofd_android.ui.screen.login.LoginWithTokenContent
import ru.dmitryskor.receipt_ofd_android.ui.screen.scanner.ScannerContent
import ru.dmitryskor.receipt_ofd_android.ui.screen.start.StartContent

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(animator = fade() + scale()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Start -> StartContent(component = child.component, modifier = Modifier.fillMaxWidth())
            is RootComponent.Child.LoginWithToken -> LoginWithTokenContent(component = child.component, modifier = Modifier.fillMaxWidth())
            is RootComponent.Child.Scanner -> ScannerContent(component = child.component, modifier = Modifier.fillMaxWidth())
        }
    }
}