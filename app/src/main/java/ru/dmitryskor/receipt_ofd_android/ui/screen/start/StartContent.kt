package ru.dmitryskor.receipt_ofd_android.ui.screen.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun StartContent(
    component: StartComponent,
    modifier: Modifier = Modifier
) {
    val state = component.state.subscribeAsState()

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            state = state.value,
            onReloadCheckServer = { component.onReloadCheckServer() }
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: StartState,
    onReloadCheckServer: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            StartState.Loading -> CircularProgressIndicator()
            is StartState.UnknownError,
            is StartState.ServerNotAvailable -> Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textError = when (state) {
                    is StartState.UnknownError -> "Неизвестная ошибка: ${state.error}"
                    is StartState.ServerNotAvailable -> "Сервер недоступен: ${state.error}"
                    StartState.Loading -> "Загрузка"
                }
                Text(textError)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onReloadCheckServer
                ) {
                    Text("Обновить")
                }
            }
        }
    }
}