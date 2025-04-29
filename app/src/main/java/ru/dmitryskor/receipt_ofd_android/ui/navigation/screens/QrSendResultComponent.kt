package ru.dmitryskor.receipt_ofd_android.ui.navigation.screens

import com.arkivanov.decompose.ComponentContext

class QrSendResultComponent(
    componentContext: ComponentContext,
    val response: String,
    val onClearStack: () -> Unit
) : ComponentContext by componentContext