package com.example.thrivein.data.network.response.waiting

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailWaitingServiceResponse(

	@field:SerializedName("icon_url")
	val iconUrl: String? = null,

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("discount")
	val discount: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("total_order")
	val totalOrder: Int? = null,

	@field:SerializedName("total_pay")
	val totalPay: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("service_id")
	val serviceId: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null,

	@field:SerializedName("payment_method")
	val paymentMethod: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
