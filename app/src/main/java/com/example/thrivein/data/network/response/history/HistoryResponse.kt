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

	@field:SerializedName("iconUrl")
	val iconUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("transactionDate")
	val transactionDate: String? = null,

	@field:SerializedName("orderId")
	val orderId: String? = null
) : Parcelable
