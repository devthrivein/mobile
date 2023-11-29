package com.example.thrivein.ui.screen.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thrivein.R
import com.example.thrivein.ui.component.item.SettingItem
import com.example.thrivein.ui.component.navigation.BottomBarNavigation
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Pink
import com.example.thrivein.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
) {
    Scaffold(
        bottomBar = {
            BottomBarNavigation(navHostController = navHostController)
        },
    ) { innerpadding ->
        val scrollState = rememberScrollState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Box(
                modifier = modifier
                    .background(Background)
                    .padding(innerpadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    //your store
                    Text(text = stringResource(id = R.string.your_store))
                    SettingItem(
                        id = "1",
                        title = stringResource(id = R.string.store_profile),
                        icon = painterResource(id = R.drawable.ic_store_profile)
                    )
                    //services
                    Text(text = stringResource(id = R.string.services))
                    SettingItem(
                        id = "2",
                        title = stringResource(id = R.string.FAQ),
                        icon = painterResource(id = R.drawable.ic_store_profile)
                    )
                    SettingItem(
                        id = "3",
                        title = stringResource(id = R.string.store_profile),
                        icon = painterResource(id = R.drawable.ic_store_profile)
                    )
                    //Authentication
                    Text(text = stringResource(id = R.string.authentication))
                    SettingItem(
                        id = "4",
                        title = stringResource(id = R.string.store_profile),
                        icon = painterResource(id = R.drawable.ic_store_profile)
                    )
                    SettingItem(
                        id = "5",
                        title = stringResource(id = R.string.store_profile),
                        icon = painterResource(id = R.drawable.ic_store_profile)
                    )
                }
            }
        }
    }

}

@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen(navHostController = rememberNavController())
}
