package ru.dmitryskor.receipt_ofd_android.di.modules

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import ru.dmitryskor.receipt_ofd_android.data.network.DefaultNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.NetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.server.DefaultServerNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.server.ServerNetworkClient
import ru.dmitryskor.receipt_ofd_android.di.qualifier.BaseUrl
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope

@Module
class ClientServerModule {

    @AppScope
    @Provides
    fun provideNetworkClient(
        client: HttpClient,
        @BaseUrl baseUrl: String
    ): NetworkClient {
        return DefaultNetworkClient(
            client = client,
            baseUrl = baseUrl
        )
    }

    @AppScope
    @Provides
    fun provideServerNetworkClient(client: NetworkClient): ServerNetworkClient {
        return DefaultServerNetworkClient(client = client)
    }
}