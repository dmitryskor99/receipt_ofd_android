package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import ru.dmitryskor.receipt_ofd_android.ui.component.QRCodeScanner
import ru.dmitryskor.receipt_ofd_android.ui.screen.permission.PermissionsScreen

@Composable
fun ScannerContent(
    modifier: Modifier = Modifier,
    component: ScannerComponent
) {
    val state = component.state.subscribeAsState()

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Content(
            modifier = Modifier.padding(innerPadding),
            state = state.value,
            onScanned = { scan ->
                component.onScanned(scan)
            },
            onClickReload = {
                component.onClickReload()
            }
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    state: ScannerState,
    onScanned: (String) -> Unit,
    onClickReload: () -> Unit
) {
    PermissionsScreen(
        permissions = listOf(Manifest.permission.CAMERA)
    ) {
        when (val requestState = state.scanRequestState) {
            is ScannerState.ScanRequestState.Non -> Non(modifier = modifier, onScanned = onScanned)
            is ScannerState.ScanRequestState.Loading -> Loading(modifier = modifier, scanResult = state.scanResult)
            is ScannerState.ScanRequestState.Error -> Error(modifier = modifier, error = requestState.error, onClickReload = onClickReload)
            is ScannerState.ScanRequestState.Success -> Success(modifier = modifier, onClickReload = onClickReload)
        }
    }
}

@Composable
private fun Success(
    modifier: Modifier = Modifier,
    onClickReload: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Успех!")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClickReload) {
            Text("Еще раз")
        }
    }
}

@Composable
private fun Error(
    modifier: Modifier = Modifier,
    error: String,
    onClickReload: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ошибка: $error")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onClickReload) {
            Text("Еще раз")
        }
    }
}

@Composable
private fun Non(
    modifier: Modifier = Modifier,
    onScanned: (String) -> Unit
) {
    QRCodeScanner(
        modifier = modifier.fillMaxSize(),
        onQrScanned = onScanned
    )
}

@Composable
private fun Loading(
    modifier: Modifier = Modifier,
    scanResult: String?
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = scanResult ?: "Ничего не отсканировалось...")
        CircularProgressIndicator()
    }
}