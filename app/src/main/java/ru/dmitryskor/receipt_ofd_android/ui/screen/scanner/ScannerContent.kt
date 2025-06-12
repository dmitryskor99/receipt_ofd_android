package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScannerContent(
    modifier: Modifier = Modifier,
    component: ScannerComponent
) {
    val state = component.state.value

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            state = state
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: ScannerState
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(state.token ?: "Пусто")
    }
}