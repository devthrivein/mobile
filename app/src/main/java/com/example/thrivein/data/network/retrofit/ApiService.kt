package com.example.thrivein.data.network.retrofit


import com.example.thrivein.data.network.request.LoginRequest
import com.example.thrivein.data.network.request.RegisterRequest
import com.example.thrivein.data.network.response.MessageResponse
import com.example.thrivein.data.network.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ApiService {

    //    AUTH
    @POST("register")
    suspend fun register(@Body request: RegisterRequest): UserResponse

    @POST("login")
    suspend fun login(@Body request: LoginRequest): UserResponse

    @POST("logout")
    suspend fun logout(): MessageResponse


}