package com.example.thrivein

import android.content.Context
import android.util.Log
import com.example.thrivein.data.local.preferences.StorePreference
import com.example.thrivein.data.local.preferences.UserPreference
import com.example.thrivein.data.local.preferences.dataUserStore
import com.example.thrivein.data.local.preferences.dataUserStoreStore
import com.example.thrivein.data.network.retrofit.ApiConfig
import com.example.thrivein.data.network.retrofit.ml.ApiConfigML
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.data.repository.auth.AuthRepository
import com.example.thrivein.data.repository.banner.BannerRepository
import com.example.thrivein.data.repository.file.FileRepository
import com.example.thrivein.data.repository.history.HistoryRepository
import com.example.thrivein.data.repository.scan.ScanRepository
import com.example.thrivein.data.repository.service.ChatRepository
import com.example.thrivein.data.repository.service.ServiceCategoryRepository
import com.example.thrivein.data.repository.service.ServiceRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideUserPreference(@ApplicationContext context: Context): UserPreference {

        try {
            return UserPreference(context.dataUserStore)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing UserPreference: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideStorePreference(@ApplicationContext context: Context): StorePreference {

        try {
            return StorePreference(context.dataUserStoreStore)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing StorePreference: ${e.message}", e)
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
    fun provideScanRepository(): ScanRepository {
        val apiServiceML = provideApiConfigML().getApiServiceML()

        try {
            return ScanRepository(apiServiceML)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ScanRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideApiConfigML(): ApiConfigML {

        try {
            return ApiConfigML()
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ApiConfigML: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository {
        val pref = provideUserPreference(context)
        val storePref = provideStorePreference(context)
        val apiService = provideApiConfig().getApiService(context)

        try {
            return AuthRepository(
                pref, storePref, apiService
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
    fun provideArticleRepository(@ApplicationContext context: Context): ArticleRepository {
        val apiService = provideApiConfig().getApiService(context)
        try {
            return ArticleRepository(apiService)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ArticleRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun providerBannerRepository(@ApplicationContext context: Context): BannerRepository {
        val apiService = provideApiConfig().getApiService(context)
        try {
            return BannerRepository(apiService)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing BannerRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun providerHistoryRepository(@ApplicationContext context: Context): HistoryRepository {
        val apiService = provideApiConfig().getApiService(context)
        try {
            return HistoryRepository(apiService)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing HistoryRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun providerChatRepository(@ApplicationContext context: Context): ChatRepository {

        val apiService = provideApiConfig().getApiService(context)

        try {
            return ChatRepository(FirebaseFirestore.getInstance(), apiService)
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing ChatRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    fun providerFileRepository(): FileRepository {
        try {
            return FileRepository(FirebaseStorage.getInstance())
        } catch (e: Exception) {
            Log.e("AppModule", "Error providing FileRepository: ${e.message}", e)
            throw e
        }
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}