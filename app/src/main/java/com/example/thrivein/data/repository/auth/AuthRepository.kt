package com.example.thrivein.data.repository.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.thrivein.data.Result
import com.example.thrivein.data.local.model.UserModel
import com.example.thrivein.data.local.preferences.UserPreference
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.UserResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val pref: UserPreference,
    private val apiService: ApiService,
) {

    fun getUser(): Flow<UserModel> {
        return pref.getUser()
    }

    suspend fun saveUser(user: UserModel) {
        pref.saveUser(user)
    }

    suspend fun logout() {
        pref.logout()
    }


    fun register(
        name: String,
        email: String,
        password: String,
        phone: String,
        storeName: String,
        storeEmail: String,
        storePhone: String,
        storeType: String,
        address: String,
    ): LiveData<Result<UserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.register(
                name = name,
                email = email,
                password = password,
                phone = phone,
                storeName, storeEmail, storePhone, storeType, address,
            )

            var user = UserModel(
                name = response.user?.name ?: "",
                email = response.user?.email ?: "",
                userId = response.user?.userId,
                phone = response.user?.phone ?: "",
                token = response.user?.token,
                avatarUrl = response.user?.avatarUrl,
            )

            saveUser(user)

            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            Log.d("AuthRepository", "register: $errorMessage ")
            emit(Result.Error(errorMessage.toString()))
        }

    }

    suspend fun registerFlow(
        name: String,
        email: String,
        password: String,
        phone: String,
        storeName: String,
        storeEmail: String,
        storePhone: String,
        storeType: String,
        address: String,
    ): Flow<UserResponse> {

        try {
            val response = apiService.register(
                name = name,
                email = email,
                password = password,
                phone = phone,
                storeName, storeEmail, storePhone, storeType, address,
            )

            val user = UserModel(
                name = response.user?.name ?: "",
                email = response.user?.email ?: "",
                userId = response.user?.userId,
                phone = response.user?.phone ?: "",
                token = response.user?.token,
                avatarUrl = response.user?.avatarUrl,
            )

            saveUser(user)

            return flowOf(response)
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            Log.d("AuthRepository", "register: $errorMessage ")
            throw Throwable(errorMessage)
        }

    }
}