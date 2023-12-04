package com.example.thrivein.ui.component.loading

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ThriveInLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(color = Color.White, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun ThriveInLoadingPreview() {
    ThriveInLoading()
}