package com.example.thrivein.data.model

import java.util.Date

data class Article(
    val id: String,
    val title: String,
    val content: String,
    val bannerUrl: String,
    val uploadedDate: Date,
)
