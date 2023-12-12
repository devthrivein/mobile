package com.example.thrivein.data.network.retrofit.ml

import com.example.thrivein.data.network.response.scan.ScanStoreResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import javax.inject.Singleton

@Singleton
interface ApiServiceML {

    @Multipart
    @POST("predict")
    suspend fun predictStore(
        @Part image: MultipartBody.Part,
    ): ScanStoreResponse

}