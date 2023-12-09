package com.example.thrivein.data.local.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Article(
    val id: String? = "",
    val title: String? = "",
    val content: String? = "",
    val bannerUrl: String? = "",
    val uploadedDate: String? = "",
): Parcelable
