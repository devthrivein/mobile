package com.example.thrivein.data.repository.banner

import android.util.Log
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.banner.BannerResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BannerRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getAllBannerSlider(): Flow<BannerResponse>{
        try {
            val response = apiService.getAllBannerSlider()
            return flow { emit(response) }
        } catch (e: retrofit2.HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown Error"
            Log.d("BannerRepository", "getAllBannerSlider: $errorMessage")
            throw Throwable(errorMessage)
        }
    }
}