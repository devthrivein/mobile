package com.example.thrivein.data.network.response.banner

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BannerResponse(

	@field:SerializedName("banners")
	val banners: List<BannersItem?>? = null
) : Parcelable
@Parcelize
data class BannersItem(

	@field:SerializedName("banner_url")
	val bannerUrl: String? = null,

	@field:SerializedName("banner_txt")
	val bannerTxt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
): Parcelable
