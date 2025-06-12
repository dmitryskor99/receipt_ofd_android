package ru.dmitryskor.receipt_ofd_android.di.modules

import dagger.Binds
import dagger.Module
import ru.dmitryskor.receipt_ofd_android.data.network.DefaultNetworkClient
import ru.dmitryskor.receipt_ofd_android.data.network.NetworkClient
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope

@Module
abstract class ClientServerModule {

    @AppScope
    @Binds
    abstract fun bindNetworkClient(client: DefaultNetworkClient): NetworkClient

//    @AppScope
//    @Binds
//    abstract fun bindServerNetworkClient(client: DefaultServerNetworkClient): ServerNetworkClient
}