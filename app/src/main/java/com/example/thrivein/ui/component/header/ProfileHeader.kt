package com.example.thrivein.ui.component.header

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    iconUrl: String,
    title: String,
    navigateBack: () -> Unit,

) {
    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { iconUrl }
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .offset(y = -30.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(bottomStart = 150.dp, bottomEnd = 150.dp))
                    .border(
                        3.dp,
                        Color.White,
                        shape = RoundedCornerShape(bottomStart = 150.dp, bottomEnd = 150.dp)
                    )
                    .shadow(
                        elevation = 40.dp,
                        RoundedCornerShape(bottomStart = 150.dp, bottomEnd = 150.dp),
                        spotColor = Primary,
                        ambientColor = Color.Black
                    )
                    .clickable {
                        launcher.launch("image/*")
                    },
                painter = painterResource(id = R.drawable.bg_profile),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,

                )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 60.dp)
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .shadow(
                            elevation = 10.dp,
                            spotColor = Primary,
                            ambientColor = Color.Blue,
                            clip = true,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(color = Background)
                        .clickable { navigateBack() },
                    imageVector = Icons.Default.ArrowBack,
                    tint = Primary,
                    contentDescription = stringResource(R.string.to_detail),
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = 50.dp),
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 45.dp)
                .fillMaxWidth(),// Adjust the offset value as needed,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .border(3.dp, Color.White, shape = CircleShape)
                    .background(Color.White),
                model = iconUrl,
                contentDescription = stringResource(id = R.string.profile),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileHeaderPreview() {
    ProfileHeader(
        title = "Store Profile",
        iconUrl = "https://th.bing.com/th/id/OIP.SdB_qPhbKS73WKzeP25VOgHaK9?rs=1&pid=ImgDetMain",
        navigateBack = {},
    )
}