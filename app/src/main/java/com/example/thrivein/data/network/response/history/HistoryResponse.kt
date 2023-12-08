package com.example.thrivein.data.network.response.history

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("history_services")
	val historyServices: List<HistoryServicesItem?>? = null
)

data class HistoryServicesItem(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("transactionDate")
	val transactionDate: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null
)
