package com.example.thrivein.data.network.response.article

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(

	@field:SerializedName("meta")
	val meta: Meta? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null
)

data class ArticlesItem(

	@field:SerializedName("article_id")
	val articleId: String? = null,

	@field:SerializedName("uploaded_date")
	val uploadedDate: String? = null,

	@field:SerializedName("banner_url")
	val bannerUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class Meta(

	@field:SerializedName("total_data")
	val totalData: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("current_page")
	val currentPage: Int? = null,

	@field:SerializedName("data_per_page")
	val dataPerPage: Int? = null
)

