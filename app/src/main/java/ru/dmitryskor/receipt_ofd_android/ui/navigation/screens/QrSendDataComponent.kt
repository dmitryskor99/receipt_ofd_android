package ru.dmitryskor.receipt_ofd_android.ui.navigation.screens

import com.arkivanov.decompose.ComponentContext
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import ru.dmitryskor.receipt_ofd_android.ScanInfo

class QrSendDataComponent(
    componentContext: ComponentContext,
    val data: ScanInfo,
    private val token: String,
    private val onShowResult: (String) -> Unit
) : ComponentContext by componentContext {

    private val httpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        }
    }

    fun send() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchSomething()
            withContext(Dispatchers.Main) {
                onShowResult(response)
            }
        }
    }

    private suspend fun fetchSomething(): String {
        val response: String = httpClient.post("http://176.108.252.60:8000/33c9d911-5f53-4e5a-8914-870615a4f10c/scan") {
            contentType(ContentType.Application.Json)
            setBody(data.map())
            headers {
                append("fsn-token", token)
            }
        }.bodyAsText()
        return response
    }

    @Serializable
    private data class QrRequest(
        @SerialName("createdDate")
        val createdDate: String?,
        @SerialName("fiscalDocumentNumber")
        val fiscalDocumentNumber: String? = null,
        @SerialName("fiscalDriveNumber")
        val fiscalDriveNumber: String? = null,
        @SerialName("fiscalSign")
        val fiscalSign: String? = null,
        @SerialName("operationType")
        val operationType: Int? = null,
        @SerialName("scanDate")
        val scanDate: String? = null,
        @SerialName("totalSum")
        val totalSum: String? = null,
    )

    private fun ScanInfo.map(): QrRequest {
        return QrRequest(
            createdDate = createdDate,
            fiscalDocumentNumber = fiscalDocumentNumber,
            fiscalDriveNumber = fiscalDriveNumber,
            fiscalSign = fiscalSign,
            operationType = operationType?.toIntOrNull(),
            scanDate = scanDate,
            totalSum = totalSum
        )
    }
}
