package com.example.thrivein

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.thrivein.ui.navigation.Screen
import com.example.thrivein.ui.screen.auth.landing.LandingScreen
import com.example.thrivein.ui.screen.auth.login.LoginScreen
import com.example.thrivein.ui.screen.auth.register.RegisterStoreScreen
import com.example.thrivein.ui.screen.auth.register.RegisterUserScreen

@Composable
fun ThriveInApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavHost(
        navController = navHostController,
        startDestination = Screen.Landing.route
    ) {
//        AUTH
        composable(route = Screen.Landing.route) {
            LandingScreen(
                navigateToLogin = {
                    navHostController.navigate(Screen.Login.route)
                },
                navigateToSignUp = {
                    navHostController.navigate(Screen.RegisterUser.route)
                },
            )
        }
        composable(route = Screen.Login.route) {
            LoginScreen(
                navigateToRegisterUser = {
                    navHostController.navigate(Screen.RegisterUser.route)
                }
            )
        }
        composable(route = Screen.RegisterUser.route) {
            RegisterUserScreen(
                navigateToLogin = {
                    navHostController.navigateUp()
                },
                navigateToRegisterStore = {
                    navHostController.navigate(Screen.RegisterStore.route)
                },
            )
        }
        composable(route = Screen.RegisterStore.route) {
            RegisterStoreScreen(navigateToLogin = {
                navHostController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Login.route) {
                        saveState = true
                    }
                }
            })
        }
    }
}