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
import ru.dmitryskor.receipt_ofd_android.domain.GetTokenUC

class DefaultScannerComponent @AssistedInject constructor(
    private val getToken: GetTokenUC,
    @Assisted componentContext: ComponentContext
) : ScannerComponent, ComponentContext by componentContext {
    private val _state: MutableValue<ScannerState> = MutableValue(ScannerState(token = null))
    private val scope = coroutineScope(CoroutineName("DefaultScannerComponent"))

    override val state: Value<ScannerState> = _state

    init {
        scope.launch(Dispatchers.IO) {
            val token = getToken()
            _state.update { it.copy(token = token) }
        }
    }
}