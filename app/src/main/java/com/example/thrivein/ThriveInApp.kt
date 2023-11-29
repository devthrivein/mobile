package com.example.thrivein

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thrivein.ui.navigation.Screen
import com.example.thrivein.ui.screen.article.DetailArticleScreen
import com.example.thrivein.ui.screen.article.ListArticleScreen
import com.example.thrivein.ui.screen.auth.landing.LandingScreen
import com.example.thrivein.ui.screen.auth.login.LoginScreen
import com.example.thrivein.ui.screen.auth.register.RegisterStoreScreen
import com.example.thrivein.ui.screen.auth.register.RegisterUserScreen
import com.example.thrivein.ui.screen.consultation.ConsultationScreen
import com.example.thrivein.ui.screen.history_service.DetailConsultHistoryServiceScreen
import com.example.thrivein.ui.screen.history_service.DetailHistoryServiceScreen
import com.example.thrivein.ui.screen.history_service.HistoryServiceScreen
import com.example.thrivein.ui.screen.home.HomeScreen
import com.example.thrivein.ui.screen.scoreAndAdvice.ScoreAndAdviceScreen
import com.example.thrivein.ui.screen.service.detail.DetailConsultServiceScreen
import com.example.thrivein.ui.screen.service.detail.DetailServiceScreen
import com.example.thrivein.ui.screen.service.detail.DetailTransactionServiceScreen
import com.example.thrivein.ui.screen.service.list.ListServiceScreen
import com.example.thrivein.ui.screen.setting.SettingScreen
import com.example.thrivein.ui.screen.storeScanner.StoreScannerScreen

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
                },
                navigateToScan = {
                    navHostController.navigate(Screen.ScanStore.route)
                },
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

//        Scan and Score
        composable(route = Screen.ScanStore.route) {
            StoreScannerScreen(
                navigateToHome = {
                    navHostController.navigate(Screen.Home.route)
                },
                navigateToScoreAndAdvice = { storeId ->
                    navHostController.navigate(Screen.ScoreStore.createRoute(storeId))
                }
            )

        }

        composable(
            route = Screen.ScoreStore.route,
            arguments = listOf(navArgument("storeId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("storeId") ?: "1"

            ScoreAndAdviceScreen(
                id = id,
                navigateToHome = {
                    navHostController.navigate(Screen.Home.route)
                },
            )

        }

//        MAIN
        composable(route = Screen.Home.route) {
            HomeScreen(
                navHostController = navHostController,
                navigateToListService = { serviceCategoryId ->
                    navHostController.navigate(Screen.ListService.createRoute(serviceCategoryId))
                },
                navigateToListArticle = {
                    navHostController.navigate(Screen.ListArticle.route)
                },
                navigateToDetailArticle = { articleId ->
                    navHostController.navigate(Screen.DetailArticle.createRoute(articleId))
                },
                navigateToScanStore = {
                    navHostController.navigate(Screen.ScanStore.route)
                }
            )
        }

        composable(route = Screen.History.route) {
            HistoryServiceScreen(
                navHostController = navHostController,
                navigateBack = {
//                    navHostController.navigate(Screen.Home.route)
                    navHostController.navigateUp()

                },
                navigateToDetailHistoryService = { historyId ->
                    navHostController.navigate(Screen.DetailHistoryService.createRoute(historyId))
                }
            )
        }

        composable(route = Screen.Consultation.route) {
            ConsultationScreen(
                navHostController = navHostController,
                navigateBack = {
                    navHostController.navigate(Screen.Home.route)
                }
            )
        }

        composable(route = Screen.Setting.route) {
            SettingScreen(
                navHostController = navHostController
            )
        }

//        List
        composable(route = Screen.ListArticle.route) {
            ListArticleScreen(navigateBack = {
                navHostController.navigateUp()
            }, navigateToDetailArticle = { articleId ->
                navHostController.navigate(Screen.DetailArticle.createRoute(articleId))
            })
        }

        composable(
            route = Screen.ListService.route,
            arguments = listOf(navArgument("serviceCategoryId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("serviceCategoryId") ?: ""

            ListServiceScreen(
                id = id,
                title = id,
                navigateBack = {
                    navHostController.navigateUp()
                },
                navigateToDetailService = { serviceId ->
                    navHostController.navigate(Screen.DetailService.createRoute(serviceId))
                }
            )
        }

//        Detail
        composable(
            route = Screen.DetailArticle.route,
            arguments = listOf(navArgument("articleId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("articleId") ?: ""

            DetailArticleScreen(id = id, navigateBack = {
                navHostController.navigateUp()
            })
        }
        composable(
            route = Screen.DetailService.route,
            arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("serviceId") ?: ""

            DetailServiceScreen(
                id = id,
                navigateBack = {
                    navHostController.navigateUp()
                },
                navigateToConsultService = { serviceId ->
                    navHostController.navigate(Screen.DetailConsultService.createRoute(serviceId))
                }
            )
        }

        composable(
            route = Screen.DetailConsultService.route,
            arguments = listOf(navArgument("serviceId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("serviceId") ?: ""

            DetailConsultServiceScreen(
                id = id,
                navigateBack = {
                    navHostController.navigateUp()
                },
                navigateToTransaction = { transactionId ->
                    navHostController.navigate(
                        Screen.DetailTransactionService.createRoute(
                            transactionId
                        )
                    )
                }
            )
        }

        composable(
            route = Screen.DetailTransactionService.route,
            arguments = listOf(navArgument("transactionId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("transactionId") ?: ""

            DetailTransactionServiceScreen(
                id = id,
                navigateBack = {
                    navHostController.navigateUp()
                },
                navigateToHistoryService = {
                    navHostController.navigate(Screen.History.route)
                }
            )
        }

        composable(
            route = Screen.DetailHistoryService.route,
            arguments = listOf(navArgument("historyId") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("historyId") ?: ""

            DetailHistoryServiceScreen(
                id = id,
                navigateBack = {
                    navHostController.navigateUp()
                },
                navigateToConsultation = {
                    navHostController.navigate(Screen.Consultation.route)
                },
                navigateToListService = { serviceCategoryId ->
                    navHostController.navigate(Screen.ListService.createRoute(serviceCategoryId))
                },
            )
        }

        composable(
            route = Screen.DetailConsultHistoryService.route,
            arguments = listOf(navArgument("consultHistoryId") { type = NavType.StringType})
        ){
            val id = it.arguments?.getString("consultHistoryId") ?: ""
            
            DetailConsultHistoryServiceScreen(
                id = id,
                navigateBack = {navHostController.navigateUp() },
            )
        }
    }
}
