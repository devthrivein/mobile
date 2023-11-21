package com.example.thrivein

import android.util.Log
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.data.repository.service.ServiceCategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideServiceCategoryRepository(): ServiceCategoryRepository {
        try {
            return ServiceCategoryRepository()
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ServiceCategoryRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideArticleRepository(): ArticleRepository {
        try {
            return ArticleRepository()
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ArticleRepository: ${e.message}", e)
            throw e
        }
    }
}