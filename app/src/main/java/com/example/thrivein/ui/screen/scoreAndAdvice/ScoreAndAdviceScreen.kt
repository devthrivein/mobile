package com.example.thrivein.ui.screen.scoreAndAdvice

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.thrivein.R
import com.example.thrivein.data.network.response.scan.ScanStoreResponse
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@Composable
fun ScoreAndAdviceScreen(
    scanStoreResponse: ScanStoreResponse,
    imageUriFromScan: Uri,
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
) {


    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    Scaffold(
        floatingActionButton = {
            ThriveInButton(
                onClick = {
                    navigateToHome()
                },
                label = stringResource(id = R.string.go_to_home),
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 24.dp),
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerpadding ->

        var selectedImageUri by remember { mutableStateOf<Uri?>(imageUriFromScan) }

        Box(
            modifier = modifier
                .fillMaxHeight()
                .padding(innerpadding)
                .background(color = Primary),
        ) {
            Image(
                modifier = Modifier.fillMaxHeight(),
                painter = painterResource(
                    id = R.drawable.bg_score_advice
                ),
                contentDescription = "",
                alignment = Alignment.TopStart
            )
            //Score
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 60.dp)
                    .fillMaxWidth(),// Adjust the offset value as needed,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                if (selectedImageUri == Uri.parse("")) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_result_photo),
                        contentDescription = stringResource(id = R.string.photo)
                    )
                } else {
                    selectedImageUri?.let { uri ->
                        Image(
                            painter = rememberImagePainter(uri),
                            contentDescription = stringResource(id = R.string.photo),
                            modifier = Modifier
                                .width((screenWidth.value * 0.6).dp)
                                .height((screenHeight.value * 0.25).dp)
                        )
                    }
                }




                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = stringResource(id = R.string.your_photo),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.White
                )
            }

            //Advice
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .background(color = Background)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.TopCenter
            ) {
                val scrollStateArticle = rememberScrollState()
                Column(
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxSize()
                        .verticalScroll(scrollStateArticle),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = scanStoreResponse.title ?: "",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = scanStoreResponse.paragraf1 ?: "",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = scanStoreResponse.paragraf2 ?: "",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = scanStoreResponse.paragraf3 ?: "",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = scanStoreResponse.paragraf4 ?: "",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = scanStoreResponse.paragraf5 ?: "",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = scanStoreResponse.paragraf6 ?: "",
                        textAlign = TextAlign.Justify,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Normal)
                    )
                    Spacer(modifier = Modifier.height((screenHeight.value * 0.2).dp))
                }

            }
        }
    }
}

@Preview
@Composable
fun ScoreAndAdviceScreenPreview() {
    ScoreAndAdviceScreen(
        scanStoreResponse = ScanStoreResponse(),
        imageUriFromScan = Uri.parse(""),
        navigateToHome = {})
}

