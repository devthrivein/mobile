package com.example.thrivein.data.network.response.history

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HistoryResponse(

	@field:SerializedName("history_order")
	val historyServices: List<HistoryServicesItem?>? = null
) : Parcelable

@Parcelize
data class HistoryServicesItem(

	@field:SerializedName("icon_url")
	val iconUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null
) : Parcelable
