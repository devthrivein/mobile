package com.example.thrivein.data.network.response.waiting

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class WaitingListResponse(

	@field:SerializedName("waiting_list_order")
	val waitingListOrder: List<WaitingListOrderItem?>? = null
) : Parcelable

@Parcelize
data class WaitingListOrderItem(

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("order_id")
	val orderId: String? = null
) : Parcelable
