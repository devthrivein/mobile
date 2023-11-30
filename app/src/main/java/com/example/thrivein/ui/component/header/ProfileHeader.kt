package com.example.thrivein.ui.component.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.R

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    iconUrl: String,
    name: String
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .offset(y = -30.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(bottomStart = 150.dp, bottomEnd = 150.dp)),
                painter = painterResource(id = R.drawable.bg_profile),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 60.dp)
                .fillMaxWidth(),// Adjust the offset value as needed,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .background(Color.White),
                model = iconUrl,
                contentDescription = stringResource(id = R.string.profile),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )

        }
    }
}

@Preview
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader(
        name = "Fika",
        iconUrl = "https://th.bing.com/th/id/OIP.SdB_qPhbKS73WKzeP25VOgHaK9?rs=1&pid=ImgDetMain",
    )
}