package ru.dmitryskor.receipt_ofd_android.data.network

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

interface NetworkClient {
    suspend fun request(block: HttpRequestBuilder.() -> Unit): HttpResponse
    suspend fun get(endpoint: String): HttpResponse
    suspend fun get(endpoint: String, queries: Map<String, String>): HttpResponse
}