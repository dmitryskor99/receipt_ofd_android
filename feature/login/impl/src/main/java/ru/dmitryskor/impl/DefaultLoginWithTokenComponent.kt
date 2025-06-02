package ru.dmitryskor.impl

import android.content.Context
import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.dmitryskor.api.LoginWithTokenComponent

class DefaultLoginWithTokenComponent @AssistedInject constructor(
    context: Context,
    @Assisted componentContext: ComponentContext
) : LoginWithTokenComponent, ComponentContext by componentContext