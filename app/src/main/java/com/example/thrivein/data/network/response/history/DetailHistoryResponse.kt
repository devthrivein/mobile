package com.example.thrivein.data.network.response.history

import com.google.gson.annotations.SerializedName

data class DetailHistoryResponse(

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("total_pay")
	val totalPay: Int? = null,

	@field:SerializedName("is_order_now")
	val isOrderNow: Boolean? = null,

	@field:SerializedName("discount")
	val discount: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null,

	@field:SerializedName("payment_method")
	val paymentMethod: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("total_order")
	val totalOrder: Int? = null
)
