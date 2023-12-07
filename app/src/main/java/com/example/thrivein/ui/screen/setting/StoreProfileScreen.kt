package com.example.thrivein.ui.screen.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.ui.component.header.ProfileHeader
import com.example.thrivein.ui.component.input.ThriveInDropdown
import com.example.thrivein.ui.component.input.ThriveInInputText

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreProfileScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val items = listOf("Online", "Offline", "Hybrid")

    val store by authViewModel.getStore().observeAsState()


    val scrollState = rememberScrollState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) {

        var name by remember { mutableStateOf(store?.storeName ?: "") }
        var email by remember { mutableStateOf(store?.storeEmail ?: "") }
        var address by remember { mutableStateOf(store?.address ?: "") }
        var phone by remember { mutableStateOf(store?.storePhone ?: "") }
        var selectedBusiness by remember {
            mutableStateOf(store?.type ?: "")
        }

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
                navigateBack = navigateBack
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
                Spacer(modifier = Modifier.height(50.dp))
            }
        }

    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun StoreProfileScreenPreview() {
    StoreProfileScreen(navigateBack = {})
}