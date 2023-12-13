package com.example.thrivein.data.repository.service


import android.util.Log
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.data.network.response.ErrorResponse
import com.example.thrivein.data.network.response.service.message.WelcomeMessageResponse
import com.example.thrivein.data.network.retrofit.ApiService
import com.example.thrivein.utils.CHATS
import com.example.thrivein.utils.CONSULTATION
import com.example.thrivein.utils.CONSULTATION_SERVICE
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import  kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    val database: FirebaseFirestore,
    val apiService: ApiService,
) {

    suspend fun getWelcomeMessageByServiceId(
        serviceId: String,
    ): Flow<WelcomeMessageResponse> {
        try {
            val response = apiService.getWelcomeMessageByServiceId(serviceId)
            return flow { emit(response) }
        } catch (e: HttpException) {
            e.printStackTrace()
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody?.message ?: "Unknown Error"
            Log.d("ChatRepository", "getWelcomeMessageByServiceId: $errorMessage")
            throw e
        }
    }


    fun sendConsultationServiceChat(
        isAdmin: Boolean = false,
        isTransactionChat: Boolean = false,
        message: String,
        fileUrl: String,
        userId: String,
        serviceId: String,
    ) {
        val chat = ChatModel(
            isAdmin, isTransactionChat, message, userId, serviceId, fileUrl,
        )

        database
            .collection(CONSULTATION_SERVICE)
            .document(serviceId)
            .collection("messages-from-$userId")
            .add(chat)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    CONSULTATION_SERVICE,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
            }
            .addOnFailureListener { e ->
                Log.w(CONSULTATION_SERVICE, "Error adding document", e)
            }

    }


    fun sendConsultationChat(
        isAdmin: Boolean = false,
        message: String,
        fileUrl: String,
        userId: String,
    ) {
        val chat = ChatModel(
            isAdmin = isAdmin,
            message = message,
            userId = userId,
            fileUrl = fileUrl,
        )

        database
            .collection(CONSULTATION)
            .document(CHATS)
            .collection("messages-from-$userId")
            .add(chat)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    CONSULTATION,
                    "DocumentSnapshot added with ID: ${documentReference.id}"
                )
            }
            .addOnFailureListener { e ->
                Log.w(CONSULTATION, "Error adding document", e)
            }

    }


}