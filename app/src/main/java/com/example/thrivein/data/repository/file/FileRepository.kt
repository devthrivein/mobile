package com.example.thrivein.data.repository.file

import android.net.Uri
import com.example.thrivein.utils.CHATS
import com.example.thrivein.utils.CONSULTATION
import com.example.thrivein.utils.CONSULTATION_SERVICE
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileRepository @Inject constructor(
    private val storage: FirebaseStorage,
) {


    fun sendFileToConsultationService(
        file: Uri,
        serviceId: String,
        userId: Long,
        callback: (Uri?) -> Unit,
    ) {
        val ref =
            "$CONSULTATION_SERVICE/$serviceId/messages-from-$userId/${file.lastPathSegment}"
        val child = storage.reference.child(ref)

        child.putFile(file)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                child.downloadUrl
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    callback(downloadUri)
                } else {
                    callback(null)
                }
            }
    }

    fun sendFileToConsultation(file: Uri, userId: Long, callback: (Uri?) -> Unit) {
        val ref =
            "$CONSULTATION/$CHATS/messages-from-$userId/${file.lastPathSegment}"
        val child = storage.reference.child(ref)

        child.putFile(file)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                child.downloadUrl
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result
                    callback(downloadUri)
                } else {
                    callback(null)
                }
            }
    }


}