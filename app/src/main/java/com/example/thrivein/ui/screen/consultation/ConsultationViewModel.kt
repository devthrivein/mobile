package com.example.thrivein.ui.screen.consultation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.data.repository.service.ChatRepository
import com.example.thrivein.utils.CHATS
import com.example.thrivein.utils.CONSULTATION
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConsultationViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val database: FirebaseFirestore,
) : ViewModel() {


    private var _messages = MutableLiveData<MutableList<ChatModel>>()
    val messages: LiveData<MutableList<ChatModel>> = _messages

    fun sendChatConsultation(
        isAdmin: Boolean = false,
        message: String,
        userId: String,
    ) {
        chatRepository.sendConsultationChat(
            isAdmin,
            message,
            userId,
        )
    }


    fun getMessages(userId: String) {
        val collectionReference = database
            .collection(CONSULTATION)
            .document(CHATS)
            .collection("messages-from-$userId")

        collectionReference
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(CONSULTATION, "Listen failed.", e)
                    return@addSnapshotListener
                }

//                val list = emptyList<Map<String, Any>>().toMutableList()

                var chats = arrayListOf<ChatModel>()

                if (value != null) {
                    for (doc in value) {
                        val data = doc.data

                        val chat = ChatModel(
                            isAdmin = data["admin"] as Boolean?,
                            userId = data["userId"] as String?,
                            createdAt = data["createdAt"] as com.google.firebase.Timestamp?,
                            message = data["message"] as String?,
                        )

//                        list.add(data)
                        chats.add(chat)
                    }
                }

//                _messages.value = list
                _messages.value = chats
            }
    }


}