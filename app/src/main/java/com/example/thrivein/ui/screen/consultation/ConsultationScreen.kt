package com.example.thrivein.ui.screen.consultation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.model.ChatModel
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.input.ThriveInChatInput
import com.example.thrivein.ui.component.item.ChatConsultItem
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConsultationScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    consultationViewModel: ConsultationViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    var chatValue by remember { mutableStateOf("") }
    val user by authViewModel.getUser().observeAsState()

    val messages: List<ChatModel> by consultationViewModel.messages.observeAsState(initial = emptyList())

    consultationViewModel.getMessages(userId = user?.userId ?: "")

    Scaffold(
        topBar = {
            DetailTopBar(
                title = stringResource(id = R.string.consultation),
                navigateBack = navigateBack,
            )
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
                    Spacer(modifier = Modifier.height(8.dp))
                    ThriveInChatInput(
                        value = chatValue,
                        onSend = {
                            if (chatValue != "") {
                                consultationViewModel.sendChatConsultation(
                                    isAdmin = false,
                                    message = chatValue,
                                    userId = user?.userId ?: "",
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
            items(items = messages, key = { it.toString() }) { chat ->

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
fun ConsultationScreenPreview() {
    ConsultationScreen(navigateBack = {})
}