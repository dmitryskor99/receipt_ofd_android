package ru.dmitryskor.receipt_ofd_android.ui.login.token

interface LoginWithTokenComponent {
    fun interface Factory {
        operator fun invoke(): LoginWithTokenComponent
    }
}