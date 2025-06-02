package ru.dmitryskor.receipt_ofd_android.di

import dagger.Component
import ru.dmitryskor.di.scopes.AppScope
import ru.dmitryskor.impl.RootComponentFactory

@AppScope
@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {

    val rootComponentFactory: RootComponentFactory
}