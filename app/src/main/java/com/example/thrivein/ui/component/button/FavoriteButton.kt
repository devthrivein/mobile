package com.example.thrivein.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Primary

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(55.dp)
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .background(color = Primary)
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_waiting_list),
            modifier = Modifier.fillMaxSize(),
            tint = Color.White,
            contentDescription = stringResource(
                R.string.waiting_list
            ),
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun FavoriteButtonPreview() {
    FavoriteButton {

    }
}