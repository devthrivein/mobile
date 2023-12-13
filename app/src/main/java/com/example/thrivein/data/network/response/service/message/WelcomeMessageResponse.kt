package com.example.thrivein.data.network.response.service.message

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class WelcomeMessageResponse(

	@field:SerializedName("welcome_message")
	val welcomeMessage: List<WelcomeMessageItem?>? = null
) : Parcelable

@Parcelize
data class WelcomeMessageItem(

	@field:SerializedName("is_admin")
	val isAdmin: Boolean? = null,

	@field:SerializedName("service_id")
	val serviceId: String? = null,

	@field:SerializedName("welcome_id")
	val welcomeId: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) : Parcelable
