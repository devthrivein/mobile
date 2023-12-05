package com.example.thrivein.ui.navigation

sealed class Screen(val route: String) {
    //    Auth
    object Landing : Screen("landing")
    object Login : Screen("login")
    object RegisterUser : Screen("register-user")
    object RegisterStore : Screen("register-store/{name}/{email}/{password}/{phone}") {
        fun createRoute(name: String, email: String, password: String, phone: String) =
            "register-store/$name/$email/$password/$phone"
    }

    //    Scan and Score
    object ScanStore : Screen("scan-store")

    object ScoreStore : Screen("score-store/{storeId}") {
        fun createRoute(storeId: String) = "score-store/$storeId"
    }

    //    Main
    object Home : Screen("home")
    object History : Screen("history")
    object Consultation : Screen("consultation")

    //  Setting
    object Setting : Screen("setting")
    object StoreProfile : Screen("home/setting-store-profile")
    object UserProfile : Screen("home/setting-user-profile")


    //    List
    object ListArticle : Screen("home/article")
    object ListService : Screen("home/list-service/{serviceCategoryId}") {
        fun createRoute(serviceCategoryId: String) = "home/list-service/$serviceCategoryId"
    }

    object WaitingListService : Screen("home/waiting-list-service")

    //    Detail
    object DetailArticle : Screen("home/article/{articleId}") {
        fun createRoute(articleId: String) = "home/article/$articleId"
    }

    object DetailService : Screen("home/service/{serviceId}") {
        fun createRoute(serviceId: String) = "home/service/$serviceId"
    }

    object DetailConsultService : Screen("home/consult-service/{serviceId}") {
        fun createRoute(serviceId: String) = "home/consult-service/$serviceId"
    }

    object DetailTransactionService : Screen("home/transaction-service/{transactionId}") {
        fun createRoute(transactionId: String) = "home/transaction-service/$transactionId"
    }

    object DetailHistoryService : Screen("home/history-service/{historyId}") {
        fun createRoute(historyId: String) = "home/history-service/$historyId"
    }

    object DetailConsultHistoryService : Screen("home/consult-history-service/{consultHistoryId}") {
        fun createRoute(consultHistoryId: String) = "home/consult-history-service/$consultHistoryId"
    }

    object DetailWaitingList : Screen("home/waiting-list-service/{detailWaitingListId}") {
        fun createRoute(detailWaitingListId: String) =
            "home/waiting-list-service/$detailWaitingListId"
    }

}
