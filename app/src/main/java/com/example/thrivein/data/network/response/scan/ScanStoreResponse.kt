package com.example.thrivein.data.network.response.scan

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScanStoreResponse(

	@field:SerializedName("result")
	val result: String? = null
) : Parcelable
