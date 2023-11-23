package com.example.thrivein.ui.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.ui.theme.DarkenPrimary

@Composable
fun ThriveInPriceButton(
    modifier: Modifier = Modifier,
    label: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(end = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = DarkenPrimary)
    ) {
        Text(text = label)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ThriveInPriceButtonPreview() {
    ThriveInButton(onClick = { /*TODO*/ }, label = "Rp 100K")
}