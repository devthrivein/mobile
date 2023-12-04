package com.example.thrivein.ui.screen.auth.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.R
import com.example.thrivein.data.network.request.LoginRequest
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.input.ThriveInInputText
import com.example.thrivein.ui.component.title.Title
import com.example.thrivein.ui.theme.Primary
import com.example.thrivein.utils.UiState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToRegisterUser: () -> Unit,
    navigateToHome: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember {
        mutableStateOf<Boolean>(false)
    }

    val scrollState = rememberScrollState()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    loginViewModel.uiLoginState.collectAsState().value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
            }

            is UiState.Success -> {
                navigateToHome()
                isLoading = false
            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                isLoading = false
            }

        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {

        Column(
            modifier = modifier
                .verticalScroll(scrollState)
                .height(screenHeight)
                .width(screenWidth)
                .padding(horizontal = 24.dp, vertical = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Title(title = stringResource(id = R.string.log_in))
                Spacer(modifier = Modifier.height(50.dp))
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.forgot_password),
                    modifier = Modifier.clickable { },
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Primary,
                        fontWeight = FontWeight.Bold,
                    ),
                )
                Spacer(modifier = Modifier.height(50.dp))
                ThriveInButton(
                    isLoading = !isLoading,
                    onClick = {
                        if (email == "" || password == "") {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "Data is invalid!",
                                    withDismissAction = true,
                                )
                            }
                        } else {
                            val loginRequest = LoginRequest(email = email, password = password)
                            loginViewModel.login(loginRequest)
                        }
                    },
                    label = stringResource(id = R.string.log_in),
                )
            }


            Row {
                Text(text = stringResource(R.string.new_to_thrivein))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.create_an_account),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = Primary,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.clickable { navigateToRegisterUser() }
                )
            }


        }

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToRegisterUser = {}, navigateToHome = {})
}