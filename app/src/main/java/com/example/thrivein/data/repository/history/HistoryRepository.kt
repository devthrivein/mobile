package com.example.thrivein.data.repository.history

import android.util.Log
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.history.DetailHistoryServiceResponse
import com.example.thrivein.data.network.response.history.HistoryResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepository @Inject constructor(
    private val apiService: ApiService,
) {
    //HISTORY SERVICE
    suspend fun getAllHistoryService(): Flow<HistoryResponse> {
        try {
            val response = apiService.getHistoryOrders()
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unkown Error"
            Log.d("HistoryRepository", "getAllHistoryService: $errorMessage")
            throw Throwable(errorMessage)
        }
    }


    //DETAIL HISTORY SERVICE
    suspend fun getDetailHistoryById(orderId: String): Flow<DetailHistoryServiceResponse> {
        try {
            val response = apiService.getHistoryOrderById(orderId)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown Error"
            Log.d("HistoryRepository", "getDetailHistoryById: $errorMessage")
            throw Throwable(errorMessage)
        }
    }
}