package ru.dmitryskor.receipt_ofd_android.ui.screen.start

import com.arkivanov.decompose.value.Value

interface StartComponent {
    val state: Value<StartState>

    fun onReloadCheckServer()
}