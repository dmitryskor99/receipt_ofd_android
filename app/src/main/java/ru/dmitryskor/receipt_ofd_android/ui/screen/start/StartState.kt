package ru.dmitryskor.receipt_ofd_android.ui.screen.start

sealed class StartState {
    data object Loading : StartState()
    data object ServerNotAvailable : StartState()
}
