package com.example.thrivein.ui.screen.auth.register

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.input.ThriveInDropdown
import com.example.thrivein.ui.component.input.ThriveInInputText
import com.example.thrivein.ui.component.title.Title
import com.example.thrivein.ui.theme.Primary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStoreScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
) {


    val items = listOf("Online", "Offline", "Hybrid")
    var selectedBusiness by remember {
        mutableStateOf("")
    }

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .height(screenHeight)
                .width(screenWidth)
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Title(title = stringResource(R.string.register_your_business))
                Spacer(modifier = Modifier.height(50.dp))
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
                    onSelected = {
                        selectedBusiness = it
                    },
                    items = items,
                )
                Spacer(modifier = Modifier.height(50.dp))
                ThriveInButton(
                    onClick = {},
                    label = stringResource(id = R.string.sign_up),
                )
                Spacer(modifier = Modifier.height(50.dp))

            }


            Row {
                Text(text = stringResource(R.string.already_have_an_account))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(id = R.string.log_in),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = Primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable { navigateToLogin() }
                )
            }


        }

    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun RegisterStoreScreenPreview() {
    RegisterStoreScreen(navigateToLogin = {})
}