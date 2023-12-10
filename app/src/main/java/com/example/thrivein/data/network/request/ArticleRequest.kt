package com.example.thrivein.data.network.request

import com.google.gson.annotations.SerializedName

data class ArticleRequest (
    @SerializedName("size") val size: Int,
    @SerializedName("page") val page: Int
)