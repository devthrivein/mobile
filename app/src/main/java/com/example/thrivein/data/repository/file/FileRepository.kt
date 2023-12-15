package com.example.thrivein.data.repository.file

import android.net.Uri
import com.example.thrivein.utils.CHATS
import com.example.thrivein.utils.CONSULTATION
import com.example.thrivein.utils.CONSULTATION_SERVICE
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FileRepository @Inject constructor(
    private val storage: FirebaseStorage,
) {


    fun sendFileToConsultationService(
        file: File,
        serviceId: String,
        userId: String,
        callback: (Uri?) -> Unit,
    ) {
        val ref =
            "$CONSULTATION_SERVICE/$serviceId/messages-from-$userId/${file.name}.${file.extension}"
        val child = storage.reference.child(ref)

        child.putFile(Uri.fromFile(file))
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                child.downloadUrl
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(task.result)
                } else {
                    callback(null)
                }
            }
    }

    fun sendFileToConsultation(file: File, userId: String, callback: (Uri?) -> Unit) {
        val ref = "$CONSULTATION/$CHATS/messages-from-$userId/${file.name}.${file.extension}"
        val child = storage.reference.child(ref)
        val fileToSend = Uri.fromFile(file)
        child.putFile(fileToSend)
            .continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                child.downloadUrl
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(task.result)
                } else {
                    callback(null)
                }
            }
    }


}