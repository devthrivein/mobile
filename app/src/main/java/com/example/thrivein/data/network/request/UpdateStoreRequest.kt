package com.example.thrivein.data.network.request

import com.google.gson.annotations.SerializedName

data class UpdateStoreRequest(
    @SerializedName("store_name") val storeName: String,
    @SerializedName("store_email") val storeEmail: String,
    @SerializedName("store_phone") val storePhone: String,
    @SerializedName("store_type") val storeType: String,
    @SerializedName("address") val address: String,
)