package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

data class ScannerState(
    val scanResult: String?,
    val scanRequestState: ScanRequestState
) {
    sealed class ScanRequestState {
        data object Non : ScanRequestState()
        data object Loading : ScanRequestState()
        data class Error(val error: String) : ScanRequestState()
        data object Success : ScanRequestState()
    }
}
