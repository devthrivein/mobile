package com.example.thrivein.data.network.response.service

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListServicesResponse(

	@field:SerializedName("list-services")
	val listServices: List<ListServicesItem?>? = null
) : Parcelable

@Parcelize
data class ListServicesItem(

	@field:SerializedName("icon_url")
	val iconUrl: String? = null,

	@field:SerializedName("service_id")
	val serviceId: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable
