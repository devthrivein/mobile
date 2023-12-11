package com.example.thrivein.data.repository.service


import android.util.Log
import com.example.thrivein.data.model.ChatModel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(
    val database: FirebaseFirestore,
) {

    fun sendConsultationServiceChat(
        isAdmin: Boolean = false,
        isTransactionChat: Boolean = false,
        message: String,
        userId: String,
        serviceId: String,
    ) {
        val chat = ChatModel(
            isAdmin, isTransactionChat, message, userId, serviceId
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

    companion object {
        private val CONSULTATION_SERVICE = "consultation_service"
    }

}