package com.example.thrivein.data.network.retrofit


import com.example.thrivein.data.network.response.UserResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ApiService {

    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("phone") phone: String,
        @Field("store_name") storeName: String,
        @Field("store_email") storeEmail: String,
        @Field("store_phone") storePhone: String,
        @Field("store_type") storeType: String,
        @Field("address") address: String,
    ): UserResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): UserResponse


}