package com.example.thrivein.ui.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Primary

@Composable
fun ThriveInChatInput(
    modifier: Modifier = Modifier,
    value: String,
    onChange: (String) -> Unit,
    onOpenFileExplorer: () -> Unit,
    onSend: () -> Unit,
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clickable { onOpenFileExplorer() },
            painter = painterResource(id = R.drawable.ic_add_photo),
            contentDescription = stringResource(
                id = R.string.add_photo
            ),
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(8.dp))

        ThriveInInputText(
            value = value,
            placeholder = stringResource(R.string.type_here),
            isNotWide = true,
            onChange = onChange,
            modifier = Modifier.width((screenWidth.value * 0.7).dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onSend) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                tint = Color.White,
                modifier = Modifier.size(36.dp),
                contentDescription = stringResource(
                    R.string.send_message
                )
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ThriveInChatInputPreview() {
    Box(
        modifier = Modifier.background(Primary)
    ) {
        ThriveInChatInput(value = "", onChange = {}, onOpenFileExplorer = {}) {

        }
    }
}