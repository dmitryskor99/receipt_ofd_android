package ru.dmitryskor.receipt_ofd_android.di

import dagger.Component
import ru.dmitryskor.receipt_ofd_android.di.modules.AppModule
import ru.dmitryskor.receipt_ofd_android.di.scopes.AppScope

@AppScope
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent