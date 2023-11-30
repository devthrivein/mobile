package com.example.thrivein.ui.component.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@Composable
fun SettingItem(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    icon: Painter,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(22.dp))
            .border(
                width = 1.dp,
                color = Primary,
                shape = RoundedCornerShape(22.dp)
            )
            .background(Color.White)
            .clickable { onClick },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier  = Modifier
                    .width(300.dp)
                    .padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = icon, contentDescription = title)
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                    maxLines = 3,
                )
            }
            Box(
                modifier = Modifier
                    .shadow(
                        elevation = 10.dp,
                        spotColor = Primary,
                        ambientColor = Color.Blue,
                        clip = true,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(color = Background),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    modifier = Modifier
                        .scale(0.7f),
                    imageVector = Icons.Default.ArrowForward,
                    tint = Primary,
                    contentDescription = stringResource(R.string.to_detail, title),
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun SettingItemPreview() {
    SettingItem(
        id = "1",
        title = "Social Media Management",
        icon = painterResource(id = R.drawable.ic_store_profile),
        onClick = {}
    )
}