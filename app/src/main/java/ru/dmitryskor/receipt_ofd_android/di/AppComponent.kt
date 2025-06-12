package ru.dmitryskor.receipt_ofd_android.di

import dagger.Component
import ru.dmitryskor.receipt_ofd_android.di.modules.AppModule
import ru.dmitryskor.receipt_ofd_android.di.modules.ClientServerModule
import ru.dmitryskor.receipt_ofd_android.di.modules.NetworkModule
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope
import ru.dmitryskor.receipt_ofd_android.ui.navigation.RootComponentFactory

@AppScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ClientServerModule::class
    ]
)
interface AppComponent {

    val rootComponentFactory: RootComponentFactory
}