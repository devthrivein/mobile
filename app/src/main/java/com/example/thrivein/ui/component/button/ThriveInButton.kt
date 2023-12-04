package com.example.thrivein.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.ui.component.loading.ThriveInLoading
import com.example.thrivein.ui.theme.Black
import com.example.thrivein.ui.theme.Primary

@Composable
fun ThriveInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
    isOutline: Boolean = false,
    isNotWide: Boolean = false,
    isLoading: Boolean? = null,
) {
    Button(
        onClick = {
            if (isLoading == true) {
                return@Button
            } else {
                onClick()
            }
        },
        modifier = if (isNotWide)
            modifier
                .clip(RoundedCornerShape(30.dp))
                .border(
                    width = 2.dp,
                    color = if (!isOutline) Color.Transparent else Primary,
                    shape = RoundedCornerShape(30.dp)
                )
                .background(color = if (!isOutline) Primary else Color.Transparent) else modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .border(
                width = 2.dp,
                color = if (!isOutline) Color.Transparent else Primary,
                shape = RoundedCornerShape(30.dp)
            )
            .background(color = if (!isOutline) Primary else Color.Transparent),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = if (!isOutline) Black else Primary
                )

            )
            if (isLoading == true) {
                Spacer(modifier = Modifier.width(8.dp))
                ThriveInLoading(modifier = Modifier.size(20.dp))
            }
        }

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ThriveInButtonPreview() {
    ThriveInButton(onClick = { /*TODO*/ }, label = "Test")
}