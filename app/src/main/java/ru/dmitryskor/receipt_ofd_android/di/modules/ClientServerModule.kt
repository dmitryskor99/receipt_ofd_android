package ru.dmitryskor.receipt_ofd_android.di.modules

import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import ru.dmitryskor.receipt_ofd_android.data.network.DefaultNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.DefaultSecureNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.NetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.scan.DefaultScanNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.scan.ScanNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.server.DefaultServerNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.server.ServerNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription.PrimitivesDataStore
import ru.dmitryskor.receipt_ofd_android.di.qualifier.BaseUrl
import ru.dmitryskor.receipt_ofd_android.di.qualifier.OpenApiClient
import ru.dmitryskor.receipt_ofd_android.di.qualifier.SecureApiClient
import ru.dmitryskor.receipt_ofd_android.di.qualifier.SecureBaseUrl
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope

@Module
class ClientServerModule {

    @OpenApiClient
    @AppScope
    @Provides
    fun provideOpenNetworkClient(
        client: HttpClient,
        @BaseUrl baseUrl: String
    ): NetworkClient {
        return DefaultNetworkClient(
            client = client,
            baseUrl = baseUrl
        )
    }

    @SecureApiClient
    @AppScope
    @Provides
    fun provideSecureNetworkClient(
        client: HttpClient,
        @SecureBaseUrl baseUrl: String,
        primitivesDataStore: PrimitivesDataStore
    ): NetworkClient {
        return DefaultSecureNetworkClient(
            client = client,
            baseUrl = baseUrl,
            primitivesDataStore = primitivesDataStore
        )
    }

    @AppScope
    @Provides
    fun provideServerNetworkClient(@OpenApiClient client: NetworkClient): ServerNetworkClient {
        return DefaultServerNetworkClient(client = client)
    }

    @AppScope
    @Provides
    fun provideDefaultScanNetworkClient(@SecureApiClient client: NetworkClient): ScanNetworkClient {
        return DefaultScanNetworkClient(client = client)
    }
}