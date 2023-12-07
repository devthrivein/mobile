package com.example.thrivein.data.local.model

import com.google.gson.annotations.SerializedName

data class Banner (
    val bannerUrl: String,
    val bannerTxt: String,
    val id: String,
    val title: String
)