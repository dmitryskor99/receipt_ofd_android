package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.dmitryskor.receipt_ofd_android.domain.SendScanUC
import ru.dmitryskor.receipt_ofd_android.ui.screen.scanner.ScannerState.ScanRequestState

class DefaultScannerComponent @AssistedInject constructor(
    private val sendScan: SendScanUC,
    @Assisted componentContext: ComponentContext
) : ScannerComponent, ComponentContext by componentContext {
    private val _state: MutableValue<ScannerState> = MutableValue(
        ScannerState(
            scanResult = null,
            scanRequestState = ScanRequestState.Non
        )
    )
    private val scope = coroutineScope(CoroutineName("DefaultScannerComponent"))

    override val state: Value<ScannerState> = _state

    override fun onScanned(scan: String) {
        sendScanCode(scan = scan)
    }

    override fun onClickRetryScan() {
        _state.update {
            it.copy(
                scanResult = null,
                scanRequestState = ScanRequestState.Non
            )
        }
    }

    override fun onClickReload() {
        state.value.scanResult?.let {
            sendScanCode(scan = it)
        } ?: onClickRetryScan()
    }

    private fun sendScanCode(scan: String) {
        _state.update {
            it.copy(
                scanResult = scan,
                scanRequestState = ScanRequestState.Loading
            )
        }
        scope.launch(Dispatchers.IO) {
            sendScan.invoke(scan = scan)
                .onSuccess {
                    _state.update {
                        it.copy(
                            scanResult = null,
                            scanRequestState = ScanRequestState.Success
                        )
                    }
                }
                .onFailure { e ->
                    _state.update {
                        it.copy(
                            scanRequestState = ScanRequestState.Error(error = e.message ?: "Неизвестная ошибка")
                        )
                    }
                }
        }
    }
}