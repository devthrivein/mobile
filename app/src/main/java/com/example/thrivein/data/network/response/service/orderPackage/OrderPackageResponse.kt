package com.example.thrivein.data.network.response.service.orderPackage

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderPackageResponse(

    @field:SerializedName("item")
    val item: List<ItemItem?>? = null,

    @field:SerializedName("total_order")
    val totalOrder: Int? = null,
) : Parcelable

@Parcelize
data class ItemItem(

    @field:SerializedName("item_id")
    val itemId: String? = null,

    @field:SerializedName("price")
    val price: Int? = null,

    @field:SerializedName("qty")
    val qty: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("image_url")
    val imageUrl: String? = null,

    ) : Parcelable
