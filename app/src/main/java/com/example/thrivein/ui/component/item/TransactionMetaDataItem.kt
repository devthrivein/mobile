package com.example.thrivein.ui.component.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.ui.theme.Primary
import java.util.Date

@Composable
fun TransactionMetaDataItem(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        MetaItem(label = "Consult date", icon = Icons.Default.DateRange, date = Date())
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun TransactionMetaDataItemPreview() {
    TransactionMetaDataItem()
}

@Composable
fun MetaItem(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    date: Date,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Primary,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )

        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "$date",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Normal),
            textAlign = TextAlign.Right,
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MetaItemPreview() {
    MetaItem(label = "Consult date", icon = Icons.Default.DateRange, date = Date())
}