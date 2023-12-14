package com.example.thrivein.data.network.response.scan

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ScanStoreResponse(

	@field:SerializedName("paragraf_2")
	val paragraf2: String? = null,

	@field:SerializedName("paragraf_1")
	val paragraf1: String? = null,

	@field:SerializedName("paragraf_4")
	val paragraf4: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("paragraf_3")
	val paragraf3: String? = null,

	@field:SerializedName("paragraf_6")
	val paragraf6: String? = null,

	@field:SerializedName("paragraf_5")
	val paragraf5: String? = null
) : Parcelable
