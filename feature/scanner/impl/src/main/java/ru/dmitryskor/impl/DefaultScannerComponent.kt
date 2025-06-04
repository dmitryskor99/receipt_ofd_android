package ru.dmitryskor.impl

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.dmitryskor.api.ScannerComponent
import ru.dmitryskor.api.ScannerState

class DefaultScannerComponent @AssistedInject constructor(
    context: Context,
    @Assisted componentContext: ComponentContext,
    @Assisted private val userToken: String
) : ScannerComponent, ComponentContext by componentContext {
    private val _state: MutableValue<ScannerState> = MutableValue(ScannerState(token = userToken))
    override val state: Value<ScannerState> = _state
}