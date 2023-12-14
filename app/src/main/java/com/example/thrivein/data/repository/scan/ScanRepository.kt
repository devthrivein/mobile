package com.example.thrivein.data.repository.scan

import android.util.Log
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.scan.ScanStoreResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScanRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun predictStore(
        image: MultipartBody.Part,
    ): Flow<ScanStoreResponse> {
        try {
            val response = apiService.predictStore(image)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown Error"
            Log.d("ScanRepository", "predictStore: $errorMessage")
            throw e
        }
    }


}