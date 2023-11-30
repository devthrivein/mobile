package com.example.thrivein.ui.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Pink
import com.example.thrivein.ui.theme.Primary

@Composable
fun SettingItemExperiment1(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    icon: Painter,
) {
    Box(
        modifier = modifier
            .height(100.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(22.dp))
            .border(
                width = 1.dp,
                color = Primary,
                shape = RoundedCornerShape(22.dp)
            )
            .background(Color.White),
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .height(118.dp)
                    .width(118.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 22.dp,
                            bottomStart = 22.dp,
                            topEnd = 50.dp,
                            bottomEnd = 50.dp
                        )
                    )
                    .background(Primary),
            ) {
                Image(
                    painter = icon,
                    contentDescription = title,
                    modifier = Modifier
                        .align(alignment = Alignment.Center)
                        .offset(x = (-10).dp)
                        .scale(0.85f)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    maxLines = 3,
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    tint = Pink,
                    contentDescription = stringResource(R.string.to_detail, title),
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = false, device = Devices.PIXEL_4)
@Composable
fun SettingItemExperiment1Preview() {
    SettingItemExperiment1(
        id = "1",
        title = "Social Media Management",
        icon = painterResource(id = R.drawable.ic_store_profile)
    )
}