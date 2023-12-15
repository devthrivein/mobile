package com.example.thrivein.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Primary

@Composable
fun ChatConsultItem(
    modifier: Modifier = Modifier,
    isAdmin: Boolean,
    content: String,
    fileUrl: String? = null,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 24.dp
            ),
        horizontalAlignment = if (isAdmin) Alignment.Start else Alignment.End,
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(if (isAdmin) Color.White else Primary)
                .padding(16.dp)
        ) {

            if (fileUrl != "") {
                AsyncImage(
                    model = fileUrl,
                    contentDescription = content,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .background(Color.Gray)

                )
            } else {
                Text(
                    text = content, style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }


        }
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ChatConsultItemPreview() {
    ChatConsultItem(
        isAdmin = true,
        content = stringResource(R.string.dummy_text)
    )
}