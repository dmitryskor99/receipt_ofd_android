package ru.dmitryskor.receipt_ofd_android.di

import dagger.BindsInstance
import dagger.Subcomponent
import ru.dmitryskor.receipt_ofd_android.di.modules.SessionModule
import ru.dmitryskor.receipt_ofd_android.di.qualifiers.UserId
import ru.dmitryskor.receipt_ofd_android.di.scopes.SessionScope

@SessionScope
@Subcomponent(
    modules = [
        SessionModule::class
    ]
)
interface SessionComponent {

    @UserId
    @SessionScope
    fun userId(): String

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance @UserId userId: String): SessionComponent
    }
}