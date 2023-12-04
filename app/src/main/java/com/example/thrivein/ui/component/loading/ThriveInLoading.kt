package com.example.thrivein.ui.component.loading

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.thrivein.ui.theme.Primary

@Composable
fun ThriveInLoading() {
    CircularProgressIndicator(color = Primary)
}

@Preview(showBackground = true)
@Composable
fun ThriveInLoadingPreview() {
    ThriveInLoading()
}