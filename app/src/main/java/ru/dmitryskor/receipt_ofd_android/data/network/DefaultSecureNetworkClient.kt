package ru.dmitryskor.receipt_ofd_android.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription.PrimitivesDataStore

class DefaultSecureNetworkClient(
    client: HttpClient,
    baseUrl: String,
    private val primitivesDataStore: PrimitivesDataStore
) : DefaultNetworkClient(client = client, baseUrl = baseUrl) {
    override suspend fun request(block: HttpRequestBuilder.() -> Unit): HttpResponse {
        val userToken = primitivesDataStore.getUserToken().token ?: error("Токен авторизации не найден")
        return super.request {
            headers.append("fsn-token", userToken)
            block()
        }
    }
}