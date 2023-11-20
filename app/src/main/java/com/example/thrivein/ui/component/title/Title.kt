package com.example.thrivein.ui.component.title

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.thrivein.ui.theme.Primary

@Composable
fun Title(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.headlineMedium.copy(
            color = Primary,
            fontWeight = FontWeight.Bold,
        ),
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun TitlePreview() {
    Title(title = "Test")
}