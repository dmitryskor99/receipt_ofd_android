package ru.dmitryskor.receipt_ofd_android

import android.app.Application
import ru.dmitryskor.receipt_ofd_android.di.AppComponent
import ru.dmitryskor.receipt_ofd_android.di.DaggerAppComponent
import ru.dmitryskor.receipt_ofd_android.di.modules.AppModule

class ReceiptOFDApp : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent get() = _appComponent!!

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(application = this))
            .build()
    }
}