package com.example.thrivein.ui.screen.service.list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.PackageItem
import com.example.thrivein.ui.component.item.ServiceListItem
import com.example.thrivein.ui.theme.Background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaitingListServiceScreen(
    id: String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetailWaitingList: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopBar(title = stringResource(id = R.string.waiting_list), navigateBack = navigateBack)
        },
        containerColor = Background,
    ) { innerPadding ->

        LazyColumn(
            modifier = modifier.padding(innerPadding)
        ) {
            items(count = 10) {
                PackageItem(
                    title = "Lorem Ipsum 1x${it + 1}",
                    qty = it,
                    price = (it + 1) * 100000,
                    bannerUrl = stringResource(
                        id = R.string.dummy_image
                    ),
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .clickable { navigateToDetailWaitingList(id) }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun WaitingListServiceScreenPreview() {
    WaitingListServiceScreen(
        id = "1",
        navigateBack = {},
        navigateToDetailWaitingList = {},
    )
}