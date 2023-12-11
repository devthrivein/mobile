package com.example.thrivein.data.repository.service


import android.util.Log
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.utils.CHATS
import com.example.thrivein.utils.CONSULTATION
import com.example.thrivein.utils.CONSULTATION_SERVICE
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


    fun sendConsultationChat(
        isAdmin: Boolean = false,
        message: String,
        userId: String,
    ) {
        val chat = ChatModel(
            isAdmin = isAdmin,
            message = message,
            userId = userId,
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