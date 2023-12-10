package com.example.thrivein.data.network.response.service.portfolio

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PortfolioResponse(

    @field:SerializedName("portfolio")
    val portfolio: List<PortfolioItem?>? = null,

    @field:SerializedName("meta")
    val meta: Meta? = null,
) : Parcelable

@Parcelize
data class PortfolioItem(

    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,

    @field:SerializedName("service_id")
    val serviceId: String? = null,

    @field:SerializedName("uploaded_date")
    val uploadedDate: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("title")
    val title: String? = null,
) : Parcelable

@Parcelize
data class Meta(

    @field:SerializedName("total_data")
    val totalData: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("current_page")
    val currentPage: Int? = null,

    @field:SerializedName("data_per_page")
    val dataPerPage: Int? = null,
) : Parcelable
