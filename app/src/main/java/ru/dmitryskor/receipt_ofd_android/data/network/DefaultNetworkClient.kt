package ru.dmitryskor.receipt_ofd_android.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import ru.dmitryskor.receipt_ofd_android.di.qualifier.BaseUrl
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope
import javax.inject.Inject

@AppScope
class DefaultNetworkClient @Inject constructor(
    private val client: HttpClient,
    @BaseUrl private val baseUrl: String
) : NetworkClient {
    override suspend fun <T> request(): T? {
        TODO()
    }

    override suspend fun get(endpoint: String): HttpResponse {
        return client.get(baseUrl + endpoint)
    }
}