package com.example.thrivein.ui.screen.history_service

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.HistoryListItem
import com.example.thrivein.ui.theme.Background
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryServiceScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetailHistoryService: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopBar(title = stringResource(id = R.string.history), navigateBack = navigateBack)
        },
        containerColor = Background,
    ) { innerPadding ->

        LazyColumn(
            modifier = modifier.padding(innerPadding)
        ) {
            items(count = 10) {
                HistoryListItem(
                    id = it.toString(),
                    title = "Application $it",
                    iconUrl = "https://cdn3d.iconscout.com/3d/premium/thumb/business-deal-3597247-3010227.png",
                    date = Date(),
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .clickable { navigateToDetailHistoryService(it.toString()) },
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun HistoryServiceScreenPreview() {
    HistoryServiceScreen(
        navigateBack = {},
        navigateToDetailHistoryService = {},
    )
}