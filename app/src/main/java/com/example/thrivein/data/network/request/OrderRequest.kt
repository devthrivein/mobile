package com.example.thrivein.data.network.request

import com.google.gson.annotations.SerializedName

data class OrderRequest(
    @SerializedName("service_id") val serviceId: String,
    @SerializedName("payment_method") val paymentMethod: String,
    @SerializedName("total_order") val totalOrder: Int,
    @SerializedName("discount") val discount: Int,
    @SerializedName("total_pay") val totalPay: Int,
)
