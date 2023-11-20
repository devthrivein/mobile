package com.example.thrivein.ui.screen.auth.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thrivein.R
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.navigation.Screen
import com.example.thrivein.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToSignUp: () -> Unit,
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Scaffold(modifier = modifier) { _ ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Primary)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.thrive_in_landing),
                contentDescription = stringResource(id = R.string.thrivein),
            )
            Image(
                painter = painterResource(id = R.drawable.landing),
                contentDescription = stringResource(
                    R.string.welcome
                ),
                modifier = Modifier
                    .height(screenHeight.div(2))
                    .width(screenWidth)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.guarding_your_business_with),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize
                        ),
                    )
                    Text(
                        text = stringResource(R.string.one_scan),
                        style = TextStyle(
                            color = Primary,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize
                        ),
                    )
                    Spacer(modifier = Modifier.height(21.dp))
                    ThriveInButton(
                        onClick = navigateToLogin,
                        label = stringResource(id = R.string.log_in),
                    )
                    Spacer(modifier = Modifier.height(21.dp))
                    ThriveInButton(
                        onClick = navigateToSignUp,
                        label = stringResource(R.string.sign_up),
                        isOutline = true,
                    )

                }


            }
        }
    }


}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LandingScreenPreview() {
    LandingScreen(navigateToLogin = {}, navigateToSignUp = {})
}