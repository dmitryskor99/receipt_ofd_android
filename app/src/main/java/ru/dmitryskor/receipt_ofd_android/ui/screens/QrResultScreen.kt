package ru.dmitryskor.receipt_ofd_android.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dmitryskor.receipt_ofd_android.ui.navigation.screens.QrResultComponent

@Composable
fun QrResultScreen(component: QrResultComponent) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        component.scanInfo?.let { safeScan ->
            TextField(
                value = component.authToken,
                label = {
                    Text("token")
                },
                onValueChange = { component.authToken = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = safeScan.createdDate ?: "",
                label = {
                    Text("createdDate")
                },
                onValueChange = { component.scanInfo = safeScan.copy(createdDate = it) }
            )
            TextField(
                value = safeScan.fiscalDocumentNumber ?: "",
                label = {
                    Text("fiscalDocumentNumber")
                },
                onValueChange = { component.scanInfo = safeScan.copy(fiscalDocumentNumber = it) }
            )
            TextField(
                value = safeScan.fiscalDriveNumber ?: "",
                label = {
                    Text("fiscalDriveNumber")
                },
                onValueChange = { component.scanInfo = safeScan.copy(fiscalDriveNumber = it) }
            )
            TextField(
                value = safeScan.fiscalSign ?: "",
                label = {
                    Text("fiscalSign")
                },
                onValueChange = { component.scanInfo = safeScan.copy(fiscalSign = it) }
            )
            TextField(
                value = safeScan.operationType.toString(),
                label = {
                    Text("operationType")
                },
                onValueChange = { component.scanInfo = safeScan.copy(operationType = it) }
            )
            TextField(
                value = safeScan.scanDate ?: "",
                label = {
                    Text("scanDate")
                },
                onValueChange = { component.scanInfo = safeScan.copy(scanDate = it) }
            )
            TextField(
                value = safeScan.totalSum ?: "",
                label = {
                    Text("totalSum")
                },
                onValueChange = { component.scanInfo = safeScan.copy(totalSum = it) }
            )
            Button(onClick = { component.onSend(safeScan, component.authToken) }) {
                Text("Next")
            }
            Button(onClick = component.onBack) {
                Text("Back")
            }
        } ?: CircularProgressIndicator()
    }
}
