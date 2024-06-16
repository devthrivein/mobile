package com.example.thrivein.ui.screen.service.detail.detailConsultService

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.data.network.response.service.message.WelcomeMessageResponse
import com.example.thrivein.ui.component.dialog.ThriveInAlertDialogPackage
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.input.ThriveInChatInput
import com.example.thrivein.ui.component.item.ChatConsultItem
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary
import kotlinx.coroutines.launch

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
    navigateToTransaction: (id: String, title: String) -> Unit,
    detailConsultServiceViewModel: DetailConsultServiceViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {

    val user by authViewModel.getUser().observeAsState()
    var chatValue by remember { mutableStateOf("") }
    var openAlertDialog by remember { mutableStateOf(false) }
    val lazyColumnListState = rememberLazyListState()
    val corroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val messages by detailConsultServiceViewModel.messages.observeAsState(initial = emptyList<ChatModel>())
    var welcomeMessages: WelcomeMessageResponse? = null
    var selectedFile by remember {
        mutableStateOf<Uri?>(null)
    }
    val fileLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedFile = uri
            detailConsultServiceViewModel.sendChatConsultService(
                isAdmin = false,
                isTransactionChat = false,
                message = chatValue,
                file = selectedFile,
                userId = user?.userId ?: 0,
                serviceId = id,
            )
        }
    }

    detailConsultServiceViewModel.getMessages(
        userId = user?.userId ?: 0,
        serviceId = id
    )

//    detailConsultServiceViewModel.uiWelcomeMessageState.collectAsState(initial = UiState.Loading).value.let { uiState ->
//        when (uiState) {
//            is UiState.Loading -> {
//
//                detailConsultServiceViewModel.getWelcomeMessageByServiceId(id)
//            }
//
//            is UiState.Success -> {
//                welcomeMessages = uiState.data
//
//            }
//
//            is UiState.Error -> {
//                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }



    when {
        openAlertDialog -> {
            ThriveInAlertDialogPackage(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog = false
                    navigateToTransaction(id, title)
                },
                title = stringResource(R.string.create_transaction_now),
                content = stringResource(R.string.message_before_order)
            )
        }
    }

    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack, actions = {

                IconButton(onClick = {
                    openAlertDialog = true
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
                                    file = null,
                                    userId = user?.userId ?: 0,
                                    serviceId = id,
                                )
                                chatValue = ""
                            }

                        },
                        onChange = {
                            chatValue = it
                        },
                        onOpenFileExplorer = {
                            fileLauncher.launch("image/*")
                        },
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
            state = lazyColumnListState,
        ) {
            corroutineScope.launch {
                if (messages.isNotEmpty()) {
                    lazyColumnListState.animateScrollToItem(0)
                }
            }

            items(items = messages, key = { it.createdAt.toString() }) { chat ->

                ChatConsultItem(
                    isAdmin = chat.isAdmin ?: false,
                    fileUrl = chat.fileUrl,
                    content = chat.message ?: ""
                )

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
        navigateToTransaction = { id, title -> },
        navigateBack = {})
}