package ru.dmitryskor.receipt_ofd_android.domain

import ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription.PrimitivesDataStore
import javax.inject.Inject

class GetTokenUC @Inject constructor(
    private val primitivesDataStore: PrimitivesDataStore
) {
    suspend operator fun invoke(): String? {
        return primitivesDataStore.getUserToken().token
    }
}