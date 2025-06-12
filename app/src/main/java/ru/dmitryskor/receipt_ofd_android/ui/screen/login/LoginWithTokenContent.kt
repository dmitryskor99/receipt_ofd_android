package ru.dmitryskor.receipt_ofd_android.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun LoginWithTokenContent(
    component: LoginWithTokenComponent,
    modifier: Modifier = Modifier
) {
    val state = component.state.subscribeAsState()

    Scaffold(
        modifier = modifier
    ) { innerPaddings ->
        Content(
            modifier = Modifier.padding(innerPaddings),
            state = state.value,
            onTokenInputChange = { newInput ->
                component.onTokenInputChange(newInput)
            },
            onNext = {
                component.onNext()
            }
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: LoginWithTokenState,
    onTokenInputChange: (String) -> Unit,
    onNext: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = state.tokenInput,
            onValueChange = {
                onTokenInputChange(it)
            }
        )
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = onNext
        ) {
            Text("Next")
        }
    }
}