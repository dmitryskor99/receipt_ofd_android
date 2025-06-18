package ru.dmitryskor.receipt_ofd_android.data.network

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod

open class DefaultNetworkClient(
    private val client: HttpClient,
    private val baseUrl: String
) : NetworkClient {
    override suspend fun request(block: HttpRequestBuilder.() -> Unit): HttpResponse {
        return client.request(block)
    }

    override suspend fun get(endpoint: String): HttpResponse {
        return get(endpoint = endpoint, queries = emptyMap())
    }

    override suspend fun get(
        endpoint: String,
        queries: Map<String, String>
    ): HttpResponse {
        return request {
            method = HttpMethod.Get
            url(urlString = baseUrl + endpoint)
            url {
                queries.forEach { key, value ->
                    parameter(key, value)
                }
            }
        }
    }
}