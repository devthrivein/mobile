package com.example.thrivein.ui.screen.consultation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.data.repository.file.FileRepository
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
    private val fileRepository: FileRepository,
    private val database: FirebaseFirestore,
) : ViewModel() {


    private var _messages = MutableLiveData<MutableList<ChatModel>>()
    val messages: LiveData<MutableList<ChatModel>> = _messages

    fun sendChatConsultation(
        isAdmin: Boolean = false,
        message: String,
        file: Uri?,
        userId: String,
    ) {

        var fileUrl: String? = null

        if (file != null) {

            fileRepository.sendFileToConsultation(file, userId) {
                fileUrl = it?.toString()

                chatRepository.sendConsultationChat(
                    isAdmin,
                    message,
                    fileUrl.toString(),
                    userId,
                )
            }

        } else {
            chatRepository.sendConsultationChat(
                isAdmin,
                message,
                fileUrl ?: "",
                userId,
            )
        }

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


                var chats = arrayListOf<ChatModel>()

                if (value != null) {
                    for (doc in value) {
                        val data = doc.data

                        val chat = ChatModel(
                            isAdmin = data["admin"] as Boolean?,
                            userId = data["userId"] as String?,
                            createdAt = data["createdAt"] as com.google.firebase.Timestamp?,
                            message = data["message"] as String?,
                            fileUrl = data["fileUrl"] as String?,
                        )

                        chats.add(chat)
                    }
                }

                _messages.value = chats
            }
    }


}