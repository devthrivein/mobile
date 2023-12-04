package com.example.thrivein.data.repository.article

import com.example.thrivein.data.dummy.ArticleDummy
import com.example.thrivein.data.local.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor() {

    private val articles = mutableListOf<Article>()


    init {
        if (articles.isEmpty()) {
            for (article in ArticleDummy.dummyArticles()) {
                articles.add(article)
            }
        }
    }


    fun getAllArticle(): Flow<List<Article>> {
        return flowOf(articles)
    }

}