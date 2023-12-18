package com.example.thrivein.ui.navigation

import android.net.Uri

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

    object ScoreAndAdviceStore : Screen("score-advice")

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
    object ListService : Screen("home/list-service/{serviceCategoryId}/{serviceTitle}") {
        fun createRoute(serviceCategoryId: String, serviceTitle: String) =
            "home/list-service/$serviceCategoryId/$serviceTitle"
    }

    object WaitingListService : Screen("home/waiting-list-service")

    //    Detail
    object DetailArticle : Screen("home/article/{articleId}/{articleTitle}") {
        fun createRoute(articleId: String, articleTitle: String) =
            "home/article/$articleId/$articleTitle"
    }

    object DetailService : Screen("home/service/{serviceId}/{title}") {
        fun createRoute(serviceId: String, title: String) = "home/service/$serviceId/$title"
    }

    //Display All Image
    object AllDisplayImage : Screen("home/portfolio/{serviceId}/{title}") {
        fun createRoute(serviceId: String, title: String) = "home/portfolio/$serviceId/$title"
    }

    object DetailConsultService : Screen("home/consult-service/{serviceId}/{serviceTitle}") {
        fun createRoute(serviceId: String, serviceTitle: String) =
            "home/consult-service/$serviceId/$serviceTitle"
    }

    object DetailTransactionService :
        Screen("home/transaction-service/{transactionId}/{serviceTitle}") {
        fun createRoute(transactionId: String, serviceTitle: String) =
            "home/transaction-service/$transactionId/$serviceTitle"
    }

    object DetailHistoryService : Screen("home/history-service/{historyId}/{historyTitle}") {
        fun createRoute(historyId: String, historyTitle: String) =
            "home/history-service/$historyId/$historyTitle"
    }

    object DetailConsultHistoryService : Screen("home/consult-history-service/{consultHistoryId}") {
        fun createRoute(consultHistoryId: String) = "home/consult-history-service/$consultHistoryId"
    }

    object DetailWaitingList :
        Screen("home/waiting-list-service/{detailWaitingListId}/{detailWaitingListTitle}") {
        fun createRoute(detailWaitingListId: String, detailWaitingListTitle: String) =
            "home/waiting-list-service/$detailWaitingListId/$detailWaitingListTitle"
    }

    object WebViewScreen : Screen("{url}") {
        fun createRoute(url: String) =
            "${Uri.encode(url)}"
    }

}
