package com.example.thrivein.data.network.retrofit


import com.example.thrivein.data.network.request.LoginRequest
import com.example.thrivein.data.network.request.RegisterRequest
import com.example.thrivein.data.network.response.MessageResponse
import com.example.thrivein.data.network.response.auth.UserResponse
import com.example.thrivein.data.network.response.service.ListServicesResponse
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.data.network.response.service.ServiceResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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


    //    HOME
    @GET("services")
    suspend fun getAllServiceCategories(): ServiceCategoriesResponse


    //    Service
    @GET("list-services/{category}")
    suspend fun getAllServices(
        @Path("category") category: String,
    ): ListServicesResponse

    @GET("detail-services/{service_id}")
    suspend fun getServiceById(
        @Path("service_id") serviceId: String,
    ): ServiceResponse
}