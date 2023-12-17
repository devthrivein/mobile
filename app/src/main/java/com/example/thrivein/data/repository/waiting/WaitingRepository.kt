package com.example.thrivein.data.repository.waiting

import android.util.Log
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.waiting.DetailWaitingServiceResponse
import com.example.thrivein.data.network.response.waiting.WaitingListResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WaitingRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getAllWaitingOrder(): Flow<WaitingListResponse> {
        try {
            val response = apiService.getWaitingOrders()
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unkown Error"
            Log.d("WaitingRepository", "getAllWaitingOrder: $errorMessage")
            throw Throwable(errorMessage)
        }
    }

    suspend fun getWaitingOrderById(orderId: String): Flow<DetailWaitingServiceResponse> {
        try {
            val response = apiService.getWaitingOrderById(orderId)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unkown Error"
            Log.d("WaitingRepository", "getWaitingOrderById: $errorMessage")
            throw Throwable(errorMessage)
        }
    }

}