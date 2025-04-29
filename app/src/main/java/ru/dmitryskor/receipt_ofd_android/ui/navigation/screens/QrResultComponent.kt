package ru.dmitryskor.receipt_ofd_android.ui.navigation.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.ComponentContext
import io.ktor.http.parseQueryString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dmitryskor.receipt_ofd_android.EMPTY
import ru.dmitryskor.receipt_ofd_android.ScanInfo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class QrResultComponent(
    componentContext: ComponentContext,
    private val result: String,
    token: String,
    val onBack: () -> Unit,
    val onSend: (ScanInfo, String) -> Unit
) : ComponentContext by componentContext {

    var scanInfo by mutableStateOf<ScanInfo?>(EMPTY)
    var authToken by mutableStateOf<String>(token)

    init {
        CoroutineScope(Dispatchers.IO).launch {
            scanInfo = parseScanInfo(result)
            println("ScanInfo создан: $scanInfo")
            // Здесь можно вызвать setState {} чтобы триггерить recomposition, если нужно
        }
    }

    private fun parseScanInfo(rawQr: String): ScanInfo {
        val params = parseQueryString(rawQr)

        val createdDateRaw = params["t"]
        val createdDate = createdDateRaw?.let { parseAndFormatQrDate(it) }

        return ScanInfo(
            createdDate = createdDate,
            fiscalDocumentNumber = params["i"],
            fiscalDriveNumber = params["fn"],
            fiscalSign = params["fp"],
            operationType = params["n"],
            scanDate = formatQrDate(Date()),
            totalSum = params["s"]
        )
    }

    private fun parseAndFormatQrDate(raw: String): String? {
        return try {
            val inputFormat = SimpleDateFormat("yyyyMMdd'T'HHmm", Locale.getDefault())
            inputFormat.timeZone = TimeZone.getTimeZone("UTC") // или local, зависит от QR-кода

            val date: Date = inputFormat.parse(raw) ?: return null

            formatQrDate(date)
        } catch (e: Exception) {
            null
        }
    }

    private fun formatQrDate(date: Date): String? {
        return try {
            val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:00.000'Z'", Locale.getDefault())
            outputFormat.timeZone = TimeZone.getTimeZone("UTC")

            outputFormat.format(date)
        } catch (e: Exception) {
            null
        }
    }
}
