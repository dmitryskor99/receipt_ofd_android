package ru.dmitryskor.impl

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import ru.dmitryskor.api.StartComponent
import ru.dmitryskor.api.StartState

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
            state = state.value
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: StartState
) {
    Text("wefkwefl")
}