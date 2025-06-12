package ru.dmitryskor.receipt_ofd_android.data.network

import io.ktor.client.statement.HttpResponse

interface NetworkClient {
    suspend fun <T> request(): T?
    suspend fun get(endpoint: String): HttpResponse
}