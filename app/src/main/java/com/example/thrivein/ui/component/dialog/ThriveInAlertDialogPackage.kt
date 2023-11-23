package com.example.thrivein.ui.component.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.thrivein.R
import com.example.thrivein.ui.component.button.ThriveInButton

@Composable
fun ThriveInAlertDialogPackage(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    title: String,
    content: String,
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = content,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.labelMedium.copy(lineHeight = 20.sp),
                )
                Spacer(modifier = Modifier.height((screenHeight.value * 0.2).dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    ThriveInButton(
                        onClick = { onDismissRequest() },
                        label = stringResource(R.string.cancel),
                        isOutline = true,
                        isNotWide = true
                    )
                    ThriveInButton(
                        onClick = { onConfirmation() },
                        label = stringResource(R.string.select),
                        isNotWide = true
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ThriveInAlertDialogPackagePreview() {
    ThriveInAlertDialogPackage(
        onDismissRequest = { /*TODO*/ },
        onConfirmation = { /*TODO*/ },
        title = "Rp 500K",
        content = "- lorem\n" +
                "- Ipsum\n" +
                "- Dolor\n" +
                "- Sit\n" +
                "- Amet"
    )
}