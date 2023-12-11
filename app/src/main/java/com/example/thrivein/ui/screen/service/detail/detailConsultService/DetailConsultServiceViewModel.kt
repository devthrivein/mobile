package com.example.thrivein.ui.screen.service.detail.detailConsultService

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thrivein.data.repository.service.ChatRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailConsultServiceViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val database: FirebaseFirestore,
) : ViewModel() {

    private var _messages = MutableLiveData(emptyList<Map<String, Any>>().toMutableList())
    val messages: LiveData<MutableList<Map<String, Any>>> = _messages

    fun sendChatConsultService(
        isAdmin: Boolean = false,
        isTransactionChat: Boolean = false,
        message: String,
        userId: String,
        serviceId: String,
    ) {
        chatRepository.sendConsultationServiceChat(
            isAdmin,
            isTransactionChat,
            message,
            userId,
            serviceId
        )
    }


    fun getMessages(userId: String, serviceId: String) {
        val collectionReference = database
            .collection(CONSULTATION_SERVICE)
            .document(serviceId)
            .collection("messages-from-$userId")

        collectionReference
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(CONSULTATION_SERVICE, "Listen failed.", e)
                    return@addSnapshotListener
                }

                val list = emptyList<Map<String, Any>>().toMutableList()

                if (value != null) {
                    for (doc in value) {
                        val data = doc.data


                        list.add(data)
                    }
                }

                _messages.value = list
            }
    }

    companion object {
        private val CONSULTATION_SERVICE = "consultation_service"
    }

}