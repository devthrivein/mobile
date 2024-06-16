package com.example.thrivein.data.model

import java.util.Date


data class ChatModel(
    val isAdmin: Boolean? = false,
    val isTransactionChat: Boolean? = false,
    val message: String?,
    val userId: Long?,
    val serviceId: String? = "",
    val fileUrl: String? = "",
    val createdAt: com.google.firebase.Timestamp? = com.google.firebase.Timestamp(
        Date()
    ),
)
