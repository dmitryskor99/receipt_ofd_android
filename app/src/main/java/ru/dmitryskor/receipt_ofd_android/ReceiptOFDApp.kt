package ru.dmitryskor.receipt_ofd_android

import android.app.Application
import ru.dmitryskor.receipt_ofd_android.di.DaggerAppComponent
import ru.dmitryskor.receipt_ofd_android.di.modules.AppModule

class ReceiptOFDApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(application = this))
            .build()
    }
}