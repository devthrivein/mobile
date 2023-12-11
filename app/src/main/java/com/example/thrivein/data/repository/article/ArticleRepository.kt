package com.example.thrivein.data.repository.article

import android.util.Log
import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getAllArticles(request: ArticleRequest): Flow<ArticlesResponse> {
        try {
            val response = apiService.getAllArticles(request.size, request.page)

            return flow { emit(response) }
        } catch (e: retrofit2.HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown Error"
            Log.d("ArticleRepository", "getAllArticlesHome: $errorMessage")
            throw e
        }
    }

}