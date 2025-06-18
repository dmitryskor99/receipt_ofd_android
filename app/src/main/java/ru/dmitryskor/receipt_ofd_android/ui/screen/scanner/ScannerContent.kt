package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import ru.dmitryskor.receipt_ofd_android.ui.component.QRCodeScanner

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

    val context = LocalContext.current

    var scanned by remember { mutableStateOf<String?>(null) }

    QRCodeScanner(
        modifier = Modifier.fillMaxSize()
    ) { code ->
        scanned = code
        Toast.makeText(context, "QR: $code", Toast.LENGTH_SHORT).show()
    }
}