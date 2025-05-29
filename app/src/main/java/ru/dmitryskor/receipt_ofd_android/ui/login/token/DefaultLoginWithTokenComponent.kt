package ru.dmitryskor.receipt_ofd_android.ui.login.token

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class DefaultLoginWithTokenComponent @AssistedInject constructor(
    context: Context,
    @Assisted componentContext: ComponentContext
) : LoginWithTokenComponent, ComponentContext by componentContext {
}