package ru.dmitryskor.receipt_ofd_android.di

import dagger.Subcomponent
import ru.dmitryskor.receipt_ofd_android.di.modules.SessionModule
import ru.dmitryskor.receipt_ofd_android.di.scopes.SessionScope

@SessionScope
@Subcomponent(
    modules = [
        SessionModule::class
    ]
)
interface SessionComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SessionComponent
    }
}