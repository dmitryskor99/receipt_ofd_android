package ru.dmitryskor.receipt_ofd_android.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription.EncryptionPrimitivesDataStore
import ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription.PrimitivesDataStore
import ru.dmitryskor.receipt_ofd_android.di.scope.AppScope

@Module
class StoreModule {

    @AppScope
    @Provides
    fun provideEncryptionPrimitivesDataStore(context: Context): PrimitivesDataStore {
        return EncryptionPrimitivesDataStore(context = context)
    }
}