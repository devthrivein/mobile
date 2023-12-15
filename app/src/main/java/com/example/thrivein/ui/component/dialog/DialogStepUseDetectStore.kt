package com.example.thrivein.ui.component.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
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
fun DialogStepUseDetectStore(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
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
                    text = stringResource(id = R.string.dialog_step_detect_store),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = stringResource(id = R.string.headline_step),
                    style = MaterialTheme.typography.labelMedium.copy(lineHeight = 20.sp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.ic_step_1),
                        contentDescription = stringResource(id = R.string.title_step_1)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.content_step_1),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.ic_step_1),
                        contentDescription = stringResource(id = R.string.title_step_2)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.content_step_2),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.clip(RoundedCornerShape(5.dp)),
                        painter = painterResource(id = R.drawable.ic_step_3),
                        contentDescription = stringResource(id = R.string.title_step_3)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(id = R.string.content_step_3),
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
                    )
                }
                Spacer(modifier = Modifier.height((screenHeight.value * 0.2).dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    ThriveInButton(
                        onClick = { onConfirmation() },
                        label = stringResource(R.string.continue_step),
                        isNotWide = true
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DialogStepUseDetectStorePreview() {
    DialogStepUseDetectStore(
        onDismissRequest = { /*TODO*/ },
        onConfirmation = { /*TODO*/ },
    )
}