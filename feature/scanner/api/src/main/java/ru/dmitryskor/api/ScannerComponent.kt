package ru.dmitryskor.api

import com.arkivanov.decompose.value.Value

interface ScannerComponent {
    val state: Value<ScannerState>
}