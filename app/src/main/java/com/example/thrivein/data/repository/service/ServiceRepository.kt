package com.example.thrivein.data.repository.service

import android.util.Log
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.service.ListServicesResponse
import com.example.thrivein.data.network.response.service.ServiceResponse
import com.example.thrivein.data.network.response.service.portfolio.PortfolioResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getAllServices(category: String): Flow<ListServicesResponse> {
        try {
            val response = apiService.getAllServices(category)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("ServiceRepository", "getAllServices: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }

    suspend fun getServiceById(serviceId: String): Flow<ServiceResponse> {
        try {
            val response = apiService.getServiceById(serviceId)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("ServiceRepository", "getServiceById: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }

    suspend fun getPortfolioByServiceId(
        serviceId: String,
        size: Int,
        page: Int,
    ): Flow<PortfolioResponse> {
        try {
            val response = apiService.getPortfolioByServiceId(serviceId, size, page)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("ServiceRepository", "getPortfolioByServiceId: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }
}