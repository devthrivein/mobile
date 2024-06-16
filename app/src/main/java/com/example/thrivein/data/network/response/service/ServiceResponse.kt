package com.example.thrivein.data.network.response.service

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceResponse(

    @field:SerializedName("iconUrl")
    val iconUrl: String? = null,

    @field:SerializedName("serviceId")
    val serviceId: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    ) : Parcelable
