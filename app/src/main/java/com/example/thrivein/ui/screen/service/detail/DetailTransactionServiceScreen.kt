package com.example.thrivein.ui.screen.service.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.PackageItem
import com.example.thrivein.ui.component.item.TransactionMetaDataItem
import com.example.thrivein.ui.theme.Background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailTransactionServiceScreen(
    id: String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {

    Scaffold(
        topBar = {
            DetailTopBar(title = id, navigateBack = navigateBack)
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 48.dp)
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp)) {
                        Text(
                            text = stringResource(R.string.payment_details),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row {
                            Text(text = stringResource(R.string.payment_method))
                        }
                    }

                }
            }
        },
        containerColor = Background,
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding)
        ) {
            TransactionMetaDataItem()

            LazyColumn(
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                stickyHeader {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                            .background(
                                Background
                            )
                    ) {
                        Text(
                            text = stringResource(R.string.detail_package),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                items(count = 10) {
                    PackageItem(
                        title = "Lorem Ipsum 1x${it + 1}",
                        qty = it,
                        price = (it + 1) * 100000,
                        bannerUrl = stringResource(
                            id = R.string.dummy_image
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
            }
        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailTransactionServiceScreenPreview() {
    DetailTransactionServiceScreen(id = "1") {

    }
}
