package com.example.thrivein.data.network.response.order

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderResponse(

	@field:SerializedName("transactionDate")
	val transactionDate: String? = null,

	@field:SerializedName("totalPay")
	val totalPay: Int? = null,

	@field:SerializedName("userIid")
	val userId: String? = null,

	@field:SerializedName("serviceId")
	val serviceId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("discount")
	val discount: Int? = null,

	@field:SerializedName("invoice")
	val invoice: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("orderId")
	val orderId: String? = null,

	@field:SerializedName("paymentMethod")
	val paymentMethod: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("totalOrder")
	val totalOrder: Int? = null
) : Parcelable
