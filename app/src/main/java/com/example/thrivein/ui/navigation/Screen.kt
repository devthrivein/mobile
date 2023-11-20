package com.example.thrivein.ui.navigation

sealed class Screen(val route: String) {
    //    Auth
    object Landing : Screen("landing")
    object Login : Screen("login")
    object RegisterUser : Screen("register-user")
    object RegisterStore : Screen("register-store")

    //    Main
    object Home : Screen("home")
    object History : Screen("history")
    object Consultation : Screen("consultation")
    object Setting : Screen("setting")
}
