package com.example.thrivein.data.network.response.article

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class DetailArticleResponse(

	@field:SerializedName("uploaded_date")
	val uploadedDate: String? = null,

	@field:SerializedName("banner_url")
	val bannerUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
) : Parcelable
