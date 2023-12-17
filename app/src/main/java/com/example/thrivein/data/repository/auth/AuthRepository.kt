package com.example.thrivein.data.repository.auth

import android.util.Log
import com.example.thrivein.data.local.preferences.StorePreference
import com.example.thrivein.data.local.preferences.UserPreference
import com.example.thrivein.data.model.StoreModel
import com.example.thrivein.data.model.UserModel
import com.example.thrivein.data.network.request.LoginRequest
import com.example.thrivein.data.network.request.RegisterRequest
import com.example.thrivein.data.network.request.UpdateStoreRequest
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.MessageResponse
import com.example.thrivein.data.network.response.auth.UserResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val pref: UserPreference,
    private val storePref: StorePreference,
    private val apiService: ApiService,
) {

    fun getUser(): Flow<UserModel> {
        return pref.getUser()
    }

    fun getStore(): Flow<StoreModel> {
        return storePref.getStore()
    }

    suspend fun saveUser(user: UserModel) {
        pref.saveUser(user)
    }

    suspend fun saveStore(store: StoreModel) {
        storePref.saveStore(store)
    }

    suspend fun logout() {
        apiService.logout()
        pref.logout()
        storePref.clearStore()
    }

    suspend fun register(request: RegisterRequest): Flow<UserResponse> {

        try {
            val response = apiService.register(request)

            val user = UserModel(
                name = response.user?.name ?: "",
                email = response.user?.email ?: "",
                userId = response.user?.userId,
                phone = response.user?.phone ?: "",
                token = response.user?.token,
                avatarUrl = response.user?.avatarUrl,
            )

            val store = StoreModel(
                storeName = response.store?.storeName ?: "",
                storeEmail = response.store?.storeEmail ?: "",
                storePhone = response.store?.storePhone ?: "",
                type = response.store?.type ?: "",
                address = response.store?.address ?: "",
            )

            saveUser(user)
            saveStore(store)

            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("AuthRepository", "register: $errorMessage ")
            throw Throwable(errorMessage)
        }

    }

    suspend fun login(request: LoginRequest): Flow<UserResponse> {

        try {
            val response = apiService.login(request)

            val user = UserModel(
                name = response.user?.name ?: "",
                email = response.user?.email ?: "",
                userId = response.user?.userId,
                phone = response.user?.phone ?: "",
                token = response.user?.token,
                avatarUrl = response.user?.avatarUrl,
            )

            val store = StoreModel(
                storeName = response.store?.storeName ?: "",
                storeEmail = response.store?.storeEmail ?: "",
                storePhone = response.store?.storePhone ?: "",
                type = response.store?.type ?: "",
                address = response.store?.address ?: "",
            )

            saveUser(user)
            saveStore(store)

            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("AuthRepository", "login: $errorMessage ")
            throw Throwable(errorMessage)
        }

    }

    suspend fun updateStore(request: UpdateStoreRequest): Flow<MessageResponse> {
        try {
            val response = apiService.updateStore(request)

            val store = StoreModel(
                storeName = request.storeName,
                storeEmail = request.storeEmail,
                storePhone = request.storePhone,
                type = request.storeType,
                address = request.address,
            )


            saveStore(store)

            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown error"
            Log.d("AuthRepository", "updateStore: $errorMessage ")
            throw Throwable(errorMessage)
        }
    }
}