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

    //    List
    object ListService : Screen("home/list-service/{serviceCategoryId}") {
        fun createRoute(serviceCategoryId: String) = "home/list-service/$serviceCategoryId"
    }

    //    Detail
    object DetailService : Screen("home/service/{serviceId}") {
        fun createRoute(serviceId: String) = "home/service/$serviceId"
    }

    object DetailConsultService : Screen("home/consult-service/{serviceId}") {
        fun createRoute(serviceId: String) = "home/consult-service/$serviceId"
    }

    object DetailTransactionService : Screen("home/transaction-service/{transactionId}") {
        fun createRoute(transactionId: String) = "home/transaction-service/$transactionId"
    }
}
