package com.example.thrivein.ui.screen.service.history

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.HistoryListItem
import com.example.thrivein.ui.component.item.ServiceListItem
import com.example.thrivein.ui.component.navigation.BottomBarNavigation
import com.example.thrivein.ui.theme.Background
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryServiceScreen(
    modifier: Modifier = Modifier,
    id: String,
    title: String = "",
    navigateBack: () -> Unit,
    date: Date,
    navigateToDetailHistoryService: (String) -> Unit,
    navHostController: NavHostController,
    ) {
    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack)
        },
        bottomBar = {
            BottomBarNavigation(navHostController = navHostController)
        },
        containerColor = Background,
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            items(count = 10) {
                HistoryListItem(
                    id = it.toString(),
                    title = "Application $it",
                    iconUrl = "https://cdn3d.iconscout.com/3d/premium/thumb/business-deal-3597247-3010227.png",
                    date = date,
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
        id = "1",
        title = "History Services",
        date = SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss",
            Locale.getDefault()
        ).parse("2023-01-01 12:30:00")
            ?: Date(),
        navigateBack = {},
        navigateToDetailHistoryService = {},
        navHostController = rememberNavController(),
        )
}