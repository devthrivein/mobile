package com.example.thrivein.ui.screen.service.detail.detailConsultService

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.data.network.response.service.message.WelcomeMessageResponse
import com.example.thrivein.data.repository.file.FileRepository
import com.example.thrivein.data.repository.service.ChatRepository
import com.example.thrivein.utils.CONSULTATION_SERVICE
import com.example.thrivein.utils.UiState
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class DetailConsultServiceViewModel @Inject constructor(
    private val chatRepository: ChatRepository,
    private val fileRepository: FileRepository,
    private val database: FirebaseFirestore,
) : ViewModel() {

    private var _messages = MutableLiveData<MutableList<ChatModel>>()
    val messages: LiveData<MutableList<ChatModel>> = _messages

    private val _uiWelcomeMessageState: MutableStateFlow<UiState<WelcomeMessageResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiWelcomeMessageState: StateFlow<UiState<WelcomeMessageResponse>>
        get() = _uiWelcomeMessageState

//    fun getWelcomeMessageByServiceId(serviceId: String) {
//        viewModelScope.launch {
//            chatRepository.getWelcomeMessageByServiceId(serviceId).catch {
//                _uiWelcomeMessageState.value = UiState.Error(it.message.toString())
//            }
//                .collect { welcomeMessage ->
//                    _uiWelcomeMessageState.value = UiState.Success(welcomeMessage)
//                }
//        }
//    }


    fun sendChatConsultService(
        isAdmin: Boolean = false,
        isTransactionChat: Boolean = false,
        message: String,
        userId: Long,
        serviceId: String,
        file: Uri?,
    ) {
        var fileUrl: String? = null

        if (file != null) {

            fileRepository.sendFileToConsultationService(file, serviceId, userId) {
                fileUrl = it?.toString()

                chatRepository.sendConsultationServiceChat(
                    isAdmin,
                    isTransactionChat,
                    message,
                    fileUrl.toString(),
                    userId,
                    serviceId,
                )
            }


        } else {
            chatRepository.sendConsultationServiceChat(
                isAdmin,
                isTransactionChat,
                message,
                fileUrl ?: "",
                userId,
                serviceId,
            )
        }


    }


    fun getMessages(userId: Long, serviceId: String) {
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

                val chats = arrayListOf<ChatModel>()

                if (value != null) {
                    for (doc in value) {
                        val data = doc.data

                        val chat = ChatModel(
                            isAdmin = data["admin"] as Boolean?,
                            userId = data["userId"] as Long?,
                            createdAt = data["createdAt"] as Timestamp?,
                            message = data["message"] as String?,
                            fileUrl = data["fileUrl"] as String?,
                            serviceId = data["serviceId"] as String?,
                            isTransactionChat = data["transactionChat"] as Boolean?
                        )

                        chats.add(chat)
                    }
                }

                _messages.value = chats
            }
    }


}