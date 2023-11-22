package com.example.thrivein.ui.screen.service.list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.ServiceListItem
import com.example.thrivein.ui.theme.Background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListServiceScreen(
    modifier: Modifier = Modifier,
    id: String,
    title: String = "",
    navigateBack: () -> Unit,
    navigateToDetailService: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack)
        },
        containerColor = Background,
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(count = 10) {
                ServiceListItem(
                    id = it.toString(),
                    title = "Service Lorem $it",
                    iconUrl = "https://cdn3d.iconscout.com/3d/premium/thumb/business-deal-3597247-3010227.png",
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .clickable { navigateToDetailService(it.toString()) },
                )
            }
        }


    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ListServiceScreenPreview() {
    ListServiceScreen(
        id = "1",
        title = "Online Solutions",
        navigateBack = {},
        navigateToDetailService = {},
    )
}