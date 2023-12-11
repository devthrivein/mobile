package com.example.thrivein.data.network.retrofit

import android.content.Context
import com.example.thrivein.data.local.preferences.UserPreference
import com.example.thrivein.data.local.preferences.dataUserStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiConfig @Inject constructor() {

    fun getApiService(context: Context): ApiService {

        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val token = getTokenFromContext(context)

            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(requestHeaders)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)

    }

    private fun getTokenFromContext(context: Context): String {
        val pref = UserPreference(context.dataUserStore)
        val user = runBlocking {
            pref.getUser().first()
        }
        return user.token ?: ""
    }

    companion object {

        //        private val BASE_URL = "https://thrivein-api-v1-ihovaneucq-et.a.run.app/"
        private val BASE_URL = "https://thrivein-api-v1-0-0-sxbz2gldiq-et.a.run.app/"
    }
}