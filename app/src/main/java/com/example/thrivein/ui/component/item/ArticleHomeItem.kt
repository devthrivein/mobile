package com.example.thrivein.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.ui.theme.Gray

@Composable
fun ArticleHomeItem(
    id: Int,
    modifier: Modifier = Modifier,
    title: String,
    content: String,
    bannerUrl: String,
) {
    Box(
        modifier = modifier
            .height(120.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = bannerUrl,
                contentDescription = title,
                modifier = Modifier
                    .height(118.dp)
                    .width(118.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Gray),
                contentScale = ContentScale.Crop,
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = content,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                    maxLines = 4,

                    )
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ArticleHomeItemPreview() {
    ArticleHomeItem(
        title = "Lorem Ipsum Dolor sit amet",
        content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt........",
        bannerUrl = "",
        id = 0,
    )
}