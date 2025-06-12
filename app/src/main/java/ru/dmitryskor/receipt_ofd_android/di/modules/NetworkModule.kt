package ru.dmitryskor.receipt_ofd_android.di.modules

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import ru.dmitryskor.receipt_ofd_android.di.qualifier.BaseUrl
import ru.dmitryskor.receipt_ofd_android.di.qualifier.FullBaseUrl
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope

@Module
class NetworkModule {

    @AppScope
    @FullBaseUrl
    @Provides
    fun provideFullBaseUrl(@BaseUrl baseUrl: String): String {
        return "$baseUrl/33c9d911-5f53-4e5a-8914-870615a4f10c"
    }

    @AppScope
    @BaseUrl
    @Provides
    fun provideBaseUrl(): String {
        return "http://176.108.252.60:8000"
    }

    @AppScope
    @Provides
    fun provideKtorEngine(): HttpClientEngine {
        return OkHttp.create {

        }
    }

    @AppScope
    @Provides
    fun provideKtorClient(
        engine: HttpClientEngine
    ): HttpClient {
        return HttpClient(engine) {
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}