package com.example.thrivein.data.network.retrofit


import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.request.LoginRequest
import com.example.thrivein.data.network.request.RegisterRequest
import com.example.thrivein.data.network.response.MessageResponse
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.network.response.auth.UserResponse
import com.example.thrivein.data.network.response.banner.BannerResponse
import com.example.thrivein.data.network.response.history.DetailHistoryServiceResponse
import com.example.thrivein.data.network.response.history.HistoryResponse
import com.example.thrivein.data.network.response.service.ListServicesResponse
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.data.network.response.service.ServiceResponse
import com.example.thrivein.ui.navigation.Screen
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
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
    @GET("banners")
    suspend fun getAllBannerSlider(): BannerResponse
    @GET("articles")
    suspend fun getAllArticlesHome(
        @Query("size") size: Int,
        @Query("page") page: Int
    ): ArticlesResponse
    //    suspend fun getAllArticlesHome( @Body request: ArticleRequest
////        @Query("size") size: Int,
////        @Query("page") page: Int
//    ): ArticlesResponse

    //    Service
    @GET("list-services/{category}")
    suspend fun getAllServices(
        @Path("category") category: String,
    ): ListServicesResponse

    @GET("detail-services/{service_id}")
    suspend fun getServiceById(
        @Path("service_id") serviceId: String,
    ): ServiceResponse

    //HISTORY
    @GET("history-order")
    suspend fun getAllHistory(): HistoryResponse

    @GET("history-order/{order_id}")
    suspend fun getDetailHistoryById(
        @Path("order_id") orderId: String,
    ): DetailHistoryServiceResponse

}