package ru.dmitryskor.receipt_ofd_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.QrSendResultComponent

@Composable
fun QrSendResultScreen(component: QrSendResultComponent) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(component.response)
        Button(
            onClick = component.onClearStack
        ) {
            Text("Restart flow")
        }
    }
}