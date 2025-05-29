package ru.dmitryskor.receipt_ofd_android

import ru.dmitryskor.receipt_ofd_android.di.SessionComponent
import ru.dmitryskor.receipt_ofd_android.di.scopes.AppScope
import javax.inject.Inject

@AppScope
class SessionManager @Inject constructor(
    private val sessionComponentFactory: SessionComponent.Factory
) {

    private var sessionComponent: SessionComponent? = null

    fun startSession(userId: String) {
        sessionComponent = sessionComponentFactory.create(userId = userId)
    }

    fun stopSession() {
        sessionComponent = null
    }

    fun isActiveSession(): Boolean = sessionComponent != null
}