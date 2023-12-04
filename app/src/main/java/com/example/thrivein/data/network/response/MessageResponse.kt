package com.example.thrivein.data.network.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class MessageResponse(

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable
