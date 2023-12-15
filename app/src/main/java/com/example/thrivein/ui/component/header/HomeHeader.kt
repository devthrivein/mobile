package com.example.thrivein.ui.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.thrivein.R
import com.example.thrivein.ui.component.button.FavoriteButton
import com.example.thrivein.ui.theme.Primary

@Composable
fun HomeHeader(
    modifier: Modifier = Modifier,
    username: String,
    navigateToWaitingList: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(R.string.hello, username),
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Text(
                text = stringResource(R.string.welcome_back),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                ),
            )
        }

        FavoriteButton(onClick = navigateToWaitingList)


    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeHeaderPreview() {
    HomeHeader(username = "Test", navigateToWaitingList = {})
}