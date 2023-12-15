package com.example.thrivein.ui.screen.history_service.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
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
fun DetailConsultHistoryServiceScreen(
    modifier: Modifier = Modifier,
    id: String,
    navigateBack: () -> Unit,
) {

    var chatValue by remember { mutableStateOf("") }
    var openAlertDialog by remember { mutableStateOf(false) }
    var price by remember {
        mutableStateOf("")
    }


    when {
        openAlertDialog -> {
            ThriveInAlertDialogPackage(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog = false
                },
                title = price,
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
            DetailTopBar(title = id, navigateBack = navigateBack, actions = {
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_thrivein),
                        contentDescription = stringResource(
                            R.string.go_to_transaction
                        ),
                        modifier = Modifier
                            .offset(x = (-30).dp)
                            .fillMaxWidth()
                            .scale(2f),
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
                    Spacer(modifier = Modifier.height(8.dp))
                    ThriveInChatInput(
                        value = chatValue, onSend = {},
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
            items(count = 10) {
                ChatConsultItem(
                    isAdmin = it % 2 == 0, content = stringResource(
                        id = R.string.dummy_text
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailConsultHistoryServiceScreenPreview() {

    DetailConsultHistoryServiceScreen(id = "1", navigateBack = {})
}