package ru.dmitryskor.api

import com.arkivanov.decompose.value.Value

interface LoginWithTokenComponent {
    val state: Value<LoginWithTokenState>

    fun onTokenInputChange(newTokenInput: String)

    fun onNext()
}