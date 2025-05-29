package ru.dmitryskor.receipt_ofd_android.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    fun providerContext(): Context = application.applicationContext
}