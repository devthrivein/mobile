package com.example.thrivein.ui.screen.setting

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.thrivein.ui.component.navigation.BottomBarNavigation

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
    ) { innerPadding ->
        Text(text = "Setting nih Bos!")
    }
}