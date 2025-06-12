package ru.dmitryskor.receipt_ofd_android.domain

import ru.dmitryskor.receipt_ofd_android.data.store.dataStore.encription.PrimitivesDataStore
import ru.dmitryskor.receipt_ofd_android.domain.models.UserCredentials
import javax.inject.Inject

class SetTokenUC @Inject constructor(
    private val primitivesDataStore: PrimitivesDataStore
) {
    suspend operator fun invoke(token: String?) {
        primitivesDataStore.setUserToken(UserCredentials(token = token))
    }
}