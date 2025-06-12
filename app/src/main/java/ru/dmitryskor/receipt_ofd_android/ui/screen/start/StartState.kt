package ru.dmitryskor.receipt_ofd_android.ui.screen.start

sealed class StartState {
    data object Loading : StartState()
    data class ServerNotAvailable(val error: String) : StartState()
    data class UnknownError(val error: String) : StartState()
}
