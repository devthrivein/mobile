package com.example.thrivein.ui.screen.storeScanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.ui.component.button.AddPhotoButton
import com.example.thrivein.ui.component.button.CameraButton
import com.example.thrivein.ui.component.button.TrashbinButton
import com.example.thrivein.ui.theme.Gray

@Composable
fun StoreScannerScreen(
    modifier: Modifier = Modifier,
    navigateToScoreAndAdvice: (String) -> Unit,
    navigateToHome: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(color = Gray)
            .padding(bottom = 50.dp),
        verticalArrangement = Arrangement.Bottom

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TrashbinButton() {}
            CameraButton() {
                navigateToHome()
            }
            AddPhotoButton() {
                navigateToScoreAndAdvice("1")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StoreScannerScreenPreview() {
    StoreScannerScreen(
        navigateToHome = {},
        navigateToScoreAndAdvice = {},
    )
}