package com.example.thrivein.ui.component.button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Primary

@Composable
fun SeeAllButton(
    modifier: Modifier = Modifier,
    label: String,
    onClickButton: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Text(
            text = stringResource(R.string.see_all),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Primary
            ),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .clickable { onClickButton() }
        )

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun SeeAllButtonPreview() {
    SeeAllButton(label = stringResource(R.string.today_s_news)) {

    }
}