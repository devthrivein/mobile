package com.example.thrivein.ui.screen.service.detail.detailConsultService

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.ui.component.dialog.ThriveInAlertDialogPackage
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.input.ThriveInChatInput
import com.example.thrivein.ui.component.item.ChatConsultItem
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun DetailConsultServiceScreen(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    navigateBack: () -> Unit,
    navigateToTransaction: (String) -> Unit,
    detailConsultServiceViewModel: DetailConsultServiceViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {

    val user by authViewModel.getUser().observeAsState()
    var chatValue by remember { mutableStateOf("") }
    var openAlertDialog by remember { mutableStateOf(false) }

    val messages: List<Map<String, Any>> by detailConsultServiceViewModel.messages.observeAsState(
        initial = emptyList<Map<String, Any>>().toMutableList()
    )

    detailConsultServiceViewModel.getMessages(userId = user?.userId ?: "", serviceId = id)

    when {
        openAlertDialog -> {
            ThriveInAlertDialogPackage(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog = false
                },
                title = title,
                content = "-lorem\n" +
                        "-Ipsum\n" +
                        "-Dolor\n" +
                        "-Sit\n" +
                        "-Amet"
            )
        }
    }

    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack, actions = {

                IconButton(onClick = {
                    openAlertDialog = true

//                    navigateToTransaction(id)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_history),
                        contentDescription = stringResource(
                            R.string.go_to_transaction
                        )
                    )
                }
            })
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Primary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    ThriveInChatInput(
                        value = chatValue,
                        onSend = {
                            if (chatValue != "") {
                                detailConsultServiceViewModel.sendChatConsultService(
                                    isAdmin = false,
                                    isTransactionChat = false,
                                    message = chatValue,
                                    userId = user?.userId ?: "",
                                    serviceId = id,
                                )
                                chatValue = ""
                            }

                        },
                        onChange = {
                            chatValue = it
                        },
                        onOpenFileExplorer = {},
                    )
                }
            }
        },
        containerColor = Background,
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            reverseLayout = true,
        ) {
            items(items = messages, key = { it["createdAt"].toString() }) {

                val chat = ChatModel(
                    isAdmin = it["admin"] as Boolean?,
                    serviceId = it["serviceId"] as String?,
                    userId = it["userId"] as String?,
                    isTransactionChat = it["transactionChat"] as Boolean?,
                    createdAt = it["createdAt"] as com.google.firebase.Timestamp?,
                    message = it["message"] as String?,
                )

                if (chat.message != "") {
                    ChatConsultItem(
                        isAdmin = chat.isAdmin ?: false,
                        content = chat.message ?: ""
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailConsultServiceScreenPreview() {

    DetailConsultServiceScreen(
        title = "test",
        id = "1",
        navigateToTransaction = {},
        navigateBack = {})
}