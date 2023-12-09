package com.example.thrivein.data.network.request

import com.google.gson.annotations.SerializedName

data class ArticleRequest (
    @SerializedName("size") val size: String,
    @SerializedName("page") val page: String
)