package ru.dmitryskor.receipt_ofd_android.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val token: String?
)