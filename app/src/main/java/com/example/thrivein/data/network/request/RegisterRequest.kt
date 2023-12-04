package com.example.thrivein.data.network.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("store_name") val storeName: String,
    @SerializedName("store_email") val storeEmail: String,
    @SerializedName("store_phone") val storePhone: String,
    @SerializedName("store_type") val storeType: String,
    @SerializedName("address") val address: String,
)
