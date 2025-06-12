package ru.dmitryskor.receipt_ofd_android.ui.screen.login

import com.arkivanov.decompose.value.Value

interface LoginWithTokenComponent {
    val state: Value<LoginWithTokenState>

    fun onTokenInputChange(newTokenInput: String)

    fun onNext()
}