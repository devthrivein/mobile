package com.example.thrivein.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreModel(
    val address: String = "",
    val storeEmail: String = "",
    val storeName: String = "",
    val storePhone: String = "",
    val type: String = "",
) : Parcelable
