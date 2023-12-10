package com.example.thrivein.data.repository.article

import android.util.Log
import com.example.thrivein.data.dummy.ArticleDummy
import com.example.thrivein.data.local.model.Article
import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    private val apiService: ApiService
) {

//    private val articles = mutableListOf<Article>()
//
//
//    init {
//        if (articles.isEmpty()) {
//            for (article in ArticleDummy.dummyArticles()) {
//                articles.add(article)
//            }
//        }
//    }

//    fun getAllArticle(): Flow<List<Article>> {
//        return flowOf(articles)
//    }

//    suspend fun getAllArticlesHome(request: ArticleRequest): Flow<ArticlesResponse>{
//        try {
//            val response = apiService.getAllArticlesHome(request.size, request.page)
//
//            val article = Article(
//
//            )
//
//            return flow { emit(response) }
//        } catch (e: retrofit2.HttpException) {
//            e.printStackTrace()
//            val jsonInString = e.response()?.errorBody()?.string()
//            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
////            Log.d("ArticleRepository", "getAllArticlesHome: ${e.response()?.code()}")
//            val errorMessage = errorBody?.message ?: "Unknown Error"
//            Log.d("ArticleRepository", "getAllArticlesHome: $errorMessage")
//            throw Throwable(errorMessage)
//        }
//    }

}