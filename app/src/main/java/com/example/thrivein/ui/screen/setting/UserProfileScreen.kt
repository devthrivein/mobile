package com.example.thrivein.ui.screen.setting

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.dialog.DialogStepUseDetectStore
import com.example.thrivein.ui.component.header.ProfileHeader
import com.example.thrivein.ui.component.input.ThriveInInputText

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
) {

    val user by authViewModel.getUser().observeAsState()


    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp


    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()


    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) {

        var name by remember { mutableStateOf(user?.name ?: "") }
        var email by remember { mutableStateOf(user?.email ?: "") }
        var password by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf(user?.phone ?: "") }
        var photo by remember { mutableStateOf(user?.avatarUrl ?: "") }
//        val iconUrl = rememberSaveable() { mutableStateOf("") }
//        val painter = rememberImagePainter(
//            if (iconUrl.value.isEmpty()) {
//                R.drawable.bg_profile
//            } else {
//                iconUrl.value
//            }
//
//        )


//        val launcher = rememberLauncherForActivityResult(
//            contract = ActivityResultContracts.GetContent()
//        ) { uri: Uri? ->
//            uri?.let { photo}
//        }
        var showEdit by remember { mutableStateOf(false) }

        when {
            showEdit -> {
                ButtonEdit(
                    onDismissRequest = { showEdit = false },
                    onContinue = { showEdit = false },
                    onClick = {}
                )
            }
        }

        Column(
            modifier = Modifier
                .height(screenHeight)
                .width(screenWidth)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileHeader(
                title = stringResource(id = R.string.profile),
                iconUrl = photo,
                navigateBack = navigateBack,
                editable = { showEdit = true},
                editProfile = {}
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                ThriveInInputText(
                    label = stringResource(R.string.name),
                    value = name,
                    onChange = {
                        name = it
                    },
                    placeholder = stringResource(R.string.enter_your_name),
                )
                Spacer(modifier = Modifier.height(24.dp))
                ThriveInInputText(
                    label = stringResource(R.string.email),
                    value = email,
                    onChange = {
                        email = it
                    },
                    placeholder = stringResource(R.string.enter_your_email_address),
                    keyboardType = KeyboardType.Email,
                )
                Spacer(modifier = Modifier.height(24.dp))
                ThriveInInputText(
                    label = stringResource(R.string.password),
                    value = password,
                    isObsecure = true,
                    onChange = {
                        password = it
                    },
                    placeholder = stringResource(R.string.enter_your_password),
                    keyboardType = KeyboardType.Password
                )
                Spacer(modifier = Modifier.height(24.dp))
                ThriveInInputText(
                    label = stringResource(R.string.phone),
                    value = phone,
                    onChange = {
                        phone = it
                    },
                    placeholder = stringResource(R.string.enter_you_phone_number),
                    keyboardType = KeyboardType.Phone,
                )
                Spacer(modifier = Modifier.height(60.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                )
                {
//                    ThriveInButton(
//                            onClick = { notification.value = "Cancel" },
//                    label = stringResource(id = R.string.cancel),
//                    isOutline = true,
//                    isNotWide = true,
//                    modifier = Modifier
//                        .fillMaxWidth(0.45f)
//                    )
//                    Spacer(modifier = Modifier.width(30.dp))
//                    ThriveInButton(
//                        onClick = { notification.value = "Save" },
//                        label = stringResource(id = R.string.btn_save),
//                        isNotWide = false,
//                    )
                }
            }
        }
    }
}

@Composable
fun ButtonEdit(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDismissRequest: () -> Unit,
    onContinue: () -> Unit,
) {
    val notification = rememberSaveable() { mutableStateOf("") }
    if (notification.value.isNotEmpty()) {
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_SHORT).show()
        notification.value = ""
    }
    ThriveInButton(
        onClick = { notification.value = "Cancel" },
        label = stringResource(id = R.string.cancel),
        isOutline = true,
        isNotWide = true,
        modifier = Modifier
            .fillMaxWidth(0.45f)
    )
    Spacer(modifier = Modifier.width(30.dp))
    ThriveInButton(
        onClick = { notification.value = "Save" },
        label = stringResource(id = R.string.btn_save),
        isNotWide = false,
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreen(navigateBack = {})
}