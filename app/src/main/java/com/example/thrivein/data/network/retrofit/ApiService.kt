package com.example.thrivein.data.network.retrofit


import com.example.thrivein.data.network.request.LoginRequest
import com.example.thrivein.data.network.request.OrderRequest
import com.example.thrivein.data.network.request.RegisterRequest
import com.example.thrivein.data.network.response.MessageResponse
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.network.response.article.DetailArticleResponse
import com.example.thrivein.data.network.response.auth.UserResponse
import com.example.thrivein.data.network.response.banner.BannerResponse
import com.example.thrivein.data.network.response.history.DetailHistoryServiceResponse
import com.example.thrivein.data.network.response.history.HistoryResponse
import com.example.thrivein.data.network.response.order.OrderResponse
import com.example.thrivein.data.network.response.scan.ScanStoreResponse
import com.example.thrivein.data.network.response.service.ListServicesResponse
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.data.network.response.service.ServiceResponse
import com.example.thrivein.data.network.response.service.message.WelcomeMessageResponse
import com.example.thrivein.data.network.response.service.orderPackage.OrderPackageResponse
import com.example.thrivein.data.network.response.service.portfolio.PortfolioResponse
import com.example.thrivein.data.network.response.waiting.WaitingListResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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
    suspend fun getAllBanner(): BannerResponse

    @GET("articles")
    suspend fun getAllArticles(
        @Query("size") size: Int,
        @Query("page") page: Int,
    ): ArticlesResponse

    @GET("articles/{article_id}")
    suspend fun getDetailArticle(
        @Path("article_id") articleId: String,
    ): DetailArticleResponse

    //    Service
    @GET("list-services/{category}")
    suspend fun getAllServices(
        @Path("category") category: String,
    ): ListServicesResponse

    @GET("detail-services/{service_id}")
    suspend fun getServiceById(
        @Path("service_id") serviceId: String,
    ): ServiceResponse

    @GET("detail-services/{service_id}/portfolio")
    suspend fun getPortfolioByServiceId(
        @Path("service_id") serviceId: String,
        @Query("size") size: Int = 10,
        @Query("page") page: Int = 1,
    ): PortfolioResponse

    @GET("detail-services/{service_id}/welcome-message")
    suspend fun getWelcomeMessageByServiceId(
        @Path("service_id") serviceId: String,
    ): WelcomeMessageResponse

//    Order

    @GET("order-packages/{service_id}")
    suspend fun getOrderPackagesByServiceId(
        @Path("service_id") serviceId: String,
    ): OrderPackageResponse

    @POST("order-now")
    suspend fun createOrderNow(@Body request: OrderRequest): OrderResponse

    @POST("order-later")
    suspend fun createOrderLater(@Body request: OrderRequest): OrderResponse


    //    History Order
    @GET("history-order")
    suspend fun getHistoryOrders(): HistoryResponse

    @GET("history-order/{order_id}")
    suspend fun getHistoryOrderById(
        @Path("order_id") orderId: String,
    ): DetailHistoryServiceResponse

    //    Waiting Order
    @GET("waiting-order")
    suspend fun getWaitingOrders(): WaitingListResponse

    @GET("waiting-order/{order_id}")
    suspend fun getWaitingOrderById(
        @Path("order_id") orderId: String,
    ): DetailHistoryServiceResponse


    //    ML for predict
    @Multipart
    @POST("predict")
    suspend fun predictStore(
        @Part image: MultipartBody.Part,
    ): ScanStoreResponse

}