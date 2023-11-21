package com.example.thrivein.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thrivein.R
import com.example.thrivein.ui.component.header.HomeHeader
import com.example.thrivein.ui.component.navigation.BottomBarNavigation
import com.example.thrivein.ui.component.slider.BannerSlider
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {


    Scaffold(
        bottomBar = {
            BottomBarNavigation(navHostController = navHostController)
        },
        containerColor = Background,
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                HomeHeader(username = "Fika", navigateToWaitingList = {})
                Spacer(modifier = Modifier.height(24.dp))
                BannerSlider()
                Column {
                    Text(
                        text = stringResource(R.string.services),
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth(),
                        rows = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        userScrollEnabled = false,
                    ) {
                        items(count = 4) { index ->
                            Box(
                                modifier = Modifier
                                    .width(160.dp)
                                    .background(
                                        color = Primary,
                                        shape = RoundedCornerShape(size = 16.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$index",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navHostController = rememberNavController())
}