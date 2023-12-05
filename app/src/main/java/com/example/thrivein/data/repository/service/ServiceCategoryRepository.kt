package com.example.thrivein.data.repository.service

import android.util.Log
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceCategoryRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getAllServiceCategories(): Flow<ServiceCategoriesResponse> {
        try {
            val response = apiService.getAllServiceCategories()
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("ServiceCategoryRepository", "getAllServiceCategories: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }

}