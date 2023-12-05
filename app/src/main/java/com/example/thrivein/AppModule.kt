package com.example.thrivein

import android.content.Context
import android.util.Log
import com.example.thrivein.data.local.preferences.UserPreference
import com.example.thrivein.data.local.preferences.dataStore
import com.example.thrivein.data.network.retrofit.ApiConfig
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.data.repository.auth.AuthRepository
import com.example.thrivein.data.repository.service.ServiceCategoryRepository
import com.example.thrivein.data.repository.service.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUserPreference(@ApplicationContext context: Context): UserPreference {

        try {
            return UserPreference(context.dataStore)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing UserPreference: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideApiConfig(): ApiConfig {

        try {
            return ApiConfig()
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ApiConfig: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository {
        val pref = provideUserPreference(context)
        val apiService = provideApiConfig().getApiService(context)

        try {
            return AuthRepository(
                pref, apiService
            )
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing AuthRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideServiceCategoryRepository(@ApplicationContext context: Context): ServiceCategoryRepository {
        val apiService = provideApiConfig().getApiService(context)

        try {
            return ServiceCategoryRepository(apiService)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ServiceCategoryRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideServiceRepository(@ApplicationContext context: Context): ServiceRepository {
        val apiService = provideApiConfig().getApiService(context)

        try {
            return ServiceRepository(apiService)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ServiceRepository: ${e.message}", e)
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