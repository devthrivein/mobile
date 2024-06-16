package com.example.thrivein.data.network.response.service

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceCategoriesResponse(

    @field:SerializedName("services")
    val services: List<ServicesItem?>? = null,
) : Parcelable

@Parcelize
data class ServicesItem(

    @field:SerializedName("iconUrl")
    val iconUrl: String? = null,

    @field:SerializedName("color")
    val color: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("category")
    val category: String? = null,

    @field:SerializedName("title")
    val title: String? = null,
) : Parcelable
