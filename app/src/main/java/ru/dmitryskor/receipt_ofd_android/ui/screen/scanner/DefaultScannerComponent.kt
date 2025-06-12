package ru.dmitryskor.receipt_ofd_android.ui.screen.scanner

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class DefaultScannerComponent @AssistedInject constructor(
    context: Context,
    @Assisted componentContext: ComponentContext,
    @Assisted private val userToken: String
) : ScannerComponent, ComponentContext by componentContext {
    private val _state: MutableValue<ScannerState> = MutableValue(ScannerState(token = userToken))
    override val state: Value<ScannerState> = _state
}