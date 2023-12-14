package com.example.thrivein.data.repository.order

import android.util.Log
import com.example.thrivein.data.network.request.OrderRequest
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.order.OrderResponse
import com.example.thrivein.data.network.response.service.orderPackage.OrderPackageResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getOrderPackageServiceId(
        serviceId: String,
    ): Flow<OrderPackageResponse> {
        try {
            val response = apiService.getOrderPackagesByServiceId(serviceId)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("OrderRepository", "getOrderPackagesByServiceId: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }

    suspend fun orderNow(
        orderRequest: OrderRequest,
    ): Flow<OrderResponse> {
        try {
            val response = apiService.createOrderNow(orderRequest)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("OrderRepository", "orderNow: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }

    suspend fun orderLater(
        orderRequest: OrderRequest,
    ): Flow<OrderResponse> {
        try {
            val response = apiService.createOrderLater(orderRequest)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("OrderRepository", "orderLater: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }

}