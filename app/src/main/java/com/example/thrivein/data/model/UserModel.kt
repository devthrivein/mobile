package com.example.thrivein.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val userId: Long? = null,
    val avatarUrl: String? = "",
    val name: String,
    val email: String,
    val phone: String,
    val token: String? = "",
) : Parcelable