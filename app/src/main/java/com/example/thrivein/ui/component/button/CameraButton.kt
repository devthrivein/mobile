package com.example.thrivein.ui.component.button

import android.graphics.Camera
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary

@Composable
fun CameraButton(
    modifier: Modifier = Modifier,
    onCLick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clip(CircleShape)
            .clickable { onCLick() }
            .background(color = Primary)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_camera),
            contentDescription = stringResource(
                id = R.string.camera
            ),
            tint = Background,

        )
    }
}

@Preview
@Composable
fun CameraButtonPreview() {
    CameraButton {

    }
}