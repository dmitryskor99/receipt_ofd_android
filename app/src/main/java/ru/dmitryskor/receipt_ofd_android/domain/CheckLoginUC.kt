package ru.dmitryskor.receipt_ofd_android.domain

import ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription.PrimitivesDataStore
import javax.inject.Inject

class CheckLoginUC @Inject constructor(
    private val primitivesDataStore: PrimitivesDataStore
) {
    suspend operator fun invoke(): Boolean {
        return primitivesDataStore.getUserToken().token != null
    }
}