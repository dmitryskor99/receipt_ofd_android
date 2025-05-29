package ru.dmitryskor.receipt_ofd_android.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dmitryskor.receipt_ofd_android.di.scopes.AppScope

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppScope
    fun providerContext(): Context = application.applicationContext
}