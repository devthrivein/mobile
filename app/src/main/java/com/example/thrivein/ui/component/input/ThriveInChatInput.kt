package com.example.thrivein.ui.component.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    onSend: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        ThriveInInputText(
            value = value,
            placeholder = stringResource(R.string.type_here),
            isNotWide = true,
        )
        IconButton(onClick = onSend, modifier = Modifier.weight(1f)) {
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
        ThriveInChatInput(value = "") {

        }
    }
}