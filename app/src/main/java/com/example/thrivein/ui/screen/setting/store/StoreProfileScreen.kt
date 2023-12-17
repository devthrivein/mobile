package com.example.thrivein.ui.screen.setting.store

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.network.request.UpdateStoreRequest
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.header.ProfileHeader
import com.example.thrivein.ui.component.input.ThriveInDropdown
import com.example.thrivein.ui.component.input.ThriveInInputText
import com.example.thrivein.utils.UiState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StoreProfileScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
    storeProfileViewModel: StoreProfileViewModel = hiltViewModel(),
) {
    val items = listOf("Online", "Offline", "Hybrid")

    val store by authViewModel.getStore().observeAsState()


    val scrollState = rememberScrollState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var photo by remember { mutableStateOf("") }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { photo }
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var selectedBusiness by remember {
        mutableStateOf("")
    }

    LaunchedEffect(store) {
        name = store?.storeName ?: ""
        email = store?.storeEmail ?: ""
        address = store?.address ?: ""
        phone = store?.storePhone ?: ""
        selectedBusiness = store?.type ?: ""
    }


    storeProfileViewModel.uiMessageResponseState.collectAsState().value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {

            }

            is UiState.Success -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        uiState.data.message ?: "",
                        withDismissAction = true,
                    )
                }
            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()

            }

        }
    }


    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ThriveInButton(
                    onClick = { navigateBack() },
                    label = stringResource(id = R.string.cancel),
                    isOutline = true,
                    isNotWide = true,
                    modifier = Modifier
                        .fillMaxWidth(0.45f)
                )
                Spacer(modifier = Modifier.width(30.dp))
                ThriveInButton(
                    onClick = {

                        if (name != "" && email != "" && phone != "" && address != "" && selectedBusiness != "") {

                            val updateStoreRequest = UpdateStoreRequest(
                                storeName = name,
                                storeEmail = email,
                                storePhone = phone,
                                storeType = selectedBusiness,
                                address = address,
                            )

                            storeProfileViewModel.updateStore(updateStoreRequest)
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Data is invalid!",
                                    withDismissAction = true,
                                )
                            }
                        }

                    },
                    label = stringResource(id = R.string.btn_save),
                    isNotWide = false,
                )
            }
        }
    ) {

        selectedBusiness = store?.type ?: ""

        Column(
            modifier = Modifier
                .height(screenHeight)
                .width(screenWidth)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileHeader(
                title = stringResource(id = R.string.store_profile),
                iconUrl = "",
                navigateBack = navigateBack,
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
                    placeholder = stringResource(R.string.enter_your_business_name),
                )
                Spacer(modifier = Modifier.height(24.dp))
                ThriveInInputText(
                    label = stringResource(R.string.email),
                    value = email,
                    onChange = {
                        email = it
                    },
                    placeholder = stringResource(R.string.enter_your_business_email),
                    keyboardType = KeyboardType.Email,
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
                Spacer(modifier = Modifier.height(24.dp))
                ThriveInInputText(
                    label = stringResource(R.string.address),
                    value = address,
                    onChange = {
                        address = it
                    },
                    placeholder = stringResource(R.string.enter_your_business_address),
                )
                Spacer(modifier = Modifier.height(24.dp))
                ThriveInDropdown(
                    label = stringResource(R.string.type_of_business),
                    selected = selectedBusiness,
                    onSelected = {
                        selectedBusiness = it
                    },
                    items = items,
                )

            }
        }

    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun StoreProfileScreenPreview() {
    StoreProfileScreen(navigateBack = {})
}