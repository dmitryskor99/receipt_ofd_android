package ru.dmitryskor.receipt_ofd_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.QrSendDataComponent

@Composable
fun QrSendDataScreen(component: QrSendDataComponent) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "createdDate = ${component.data.createdDate}"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "fiscalDocumentNumber = ${component.data.fiscalDocumentNumber}"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "fiscalDriveNumber = ${component.data.fiscalDriveNumber}"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "fiscalSign = ${component.data.fiscalSign}"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "operationType = ${component.data.operationType}"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "scanDate = ${component.data.scanDate}"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "totalSum = ${component.data.totalSum}"
        )

        Button(
            onClick = { component.send() }
        ) {
            Text("Send")
        }
    }
}