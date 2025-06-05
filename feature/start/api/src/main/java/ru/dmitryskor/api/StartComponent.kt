package ru.dmitryskor.api

import com.arkivanov.decompose.value.Value

interface StartComponent {
    val state: Value<StartState>

    fun toLogin()

    fun toApp()
}