package com.example.thrivein

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.thrivein.data.network.response.scan.ScanStoreResponse
import com.example.thrivein.ui.component.navigation.BottomBarNavigation
import com.example.thrivein.ui.navigation.Screen
import com.example.thrivein.ui.screen.article.detail.DetailArticleScreen
import com.example.thrivein.ui.screen.article.list.ListArticleScreen
import com.example.thrivein.ui.screen.auth.landing.LandingScreen
import com.example.thrivein.ui.screen.auth.login.LoginScreen
import com.example.thrivein.ui.screen.auth.register.RegisterStoreScreen
import com.example.thrivein.ui.screen.auth.register.RegisterUserScreen
import com.example.thrivein.ui.screen.consultation.ConsultationScreen
import com.example.thrivein.ui.screen.history_service.DetailWaitingListScreen
import com.example.thrivein.ui.screen.history_service.detail.DetailConsultHistoryServiceScreen
import com.example.thrivein.ui.screen.history_service.detail.DetailHistoryServiceScreen
import com.example.thrivein.ui.screen.history_service.list.HistoryServiceScreen
import com.example.thrivein.ui.screen.home.HomeScreen
import com.example.thrivein.ui.screen.scoreAndAdvice.ScoreAndAdviceScreen
import com.example.thrivein.ui.screen.service.detail.detailConsultService.DetailConsultServiceScreen
import com.example.thrivein.ui.screen.service.detail.detailService.AllDisplayImageScreen
import com.example.thrivein.ui.screen.service.detail.detailService.DetailServiceScreen
import com.example.thrivein.ui.screen.service.detail.detailTransactionService.DetailTransactionServiceScreen
import com.example.thrivein.ui.screen.service.list.ListServiceScreen
import com.example.thrivein.ui.screen.setting.SettingScreen
import com.example.thrivein.ui.screen.setting.StoreProfileScreen
import com.example.thrivein.ui.screen.setting.UserProfileScreen
import com.example.thrivein.ui.screen.storeScanner.StoreScannerScreen
import com.example.thrivein.ui.screen.waiting_service.list.WaitingListServiceScreen
import com.example.thrivein.ui.screen.webView.WebViewScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThriveInApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route


    val user by authViewModel.getUser().observeAsState()

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (
                currentRoute == Screen.Home.route
                || currentRoute == Screen.History.route
                || currentRoute == Screen.Setting.route
            ) {
                BottomBarNavigation(navHostController = navHostController)
            }

        },
    ) { innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = if (user?.token.toString() != "null") Screen.Home.route else Screen.Landing.route,
            modifier = Modifier.padding(innerPadding)
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
                    navigateToHome = {
                        navHostController.navigate(Screen.Home.route) {
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    },

                    )
            }
            composable(route = Screen.RegisterUser.route) {
                RegisterUserScreen(
                    navigateToLogin = {
                        navHostController.navigateUp()
                    },
                    navigateToRegisterStore = { name, email, password, phone ->
                        navHostController.navigate(
                            Screen.RegisterStore.createRoute(
                                name = name,
                                email,
                                password,
                                phone
                            )
                        )
                    },
                )
            }
            composable(
                route = Screen.RegisterStore.route,
                arguments = listOf(
                    navArgument("email") { type = NavType.StringType },
                    navArgument("phone") { type = NavType.StringType },
                    navArgument("name") { type = NavType.StringType },
                    navArgument("password") { type = NavType.StringType },
                )
            ) {

                val name = it.arguments?.getString("name") ?: ""
                val email = it.arguments?.getString("email") ?: ""
                val password = it.arguments?.getString("password") ?: ""
                val phone = it.arguments?.getString("phone") ?: ""

                RegisterStoreScreen(
                    name = name,
                    email = email,
                    password = password,
                    phone = phone,
                    navigateToLogin = {
                        navHostController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Landing.route) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    navigateToScanStore = {
                        navHostController.navigate(Screen.ScanStore.route) {
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    })
            }

//        Scan and Score
            composable(route = Screen.ScanStore.route) {
                StoreScannerScreen(
                    navigateToHome = {
                        navHostController.navigate(Screen.Home.route) {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                            }

                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    navigateToScoreAndAdvice = { scanResponse, imageUriFromScan ->
                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "scanResponse",
                            value = scanResponse,
                        )
                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "imageUriFromScan",
                            value = imageUriFromScan,
                        )
                        navHostController.navigate(Screen.ScoreAndAdviceStore.route)
                    }
                )

            }

            composable(
                route = Screen.ScoreAndAdviceStore.route,
            ) {
                val scanResponse =
                    navHostController.previousBackStackEntry?.savedStateHandle?.get<ScanStoreResponse>(
                        "scanResponse"
                    )
                        ?: ScanStoreResponse()

                val imageUriFromScan =
                    navHostController.previousBackStackEntry?.savedStateHandle?.get<Uri>(
                        "imageUriFromScan"
                    )
                        ?: Uri.parse("")

                ScoreAndAdviceScreen(
                    scanStoreResponse = scanResponse,
                    imageUriFromScan = imageUriFromScan,
                    navigateToHome = {
                        navHostController.navigate(Screen.Home.route) {
                            popUpTo(navHostController.graph.startDestinationId) {

                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                )

            }

//        MAIN
            composable(route = Screen.Home.route) {
                HomeScreen(
                    navigateToListService = { serviceCategoryId, serviceTitle ->
                        navHostController.navigate(
                            Screen.ListService.createRoute(
                                serviceCategoryId,
                                serviceTitle
                            )
                        )
                    },
                    navigateToListArticle = {
                        navHostController.navigate(Screen.ListArticle.route)
                    },
                    navigateToDetailArticle = { articleId, articleTitle ->
                        navHostController.navigate(
                            Screen.DetailArticle.createRoute(
                                articleId,
                                articleTitle
                            )
                        )
                    },
                    navigateToScanStore = {
                        navHostController.navigate(Screen.ScanStore.route)
                    },
                    navigateToWaitingList = {
                        navHostController.navigate(Screen.WaitingListService.route)
                    },
                    navigateToBanner = { bannerUrl ->
                        navHostController.navigate(Screen.WebViewScreen.createRoute(bannerUrl))
                    }
                )
            }

            composable(route = Screen.History.route) {
                HistoryServiceScreen(
                    navigateBack = {
                        navHostController.navigateUp()

                    },
                    navigateToDetailHistoryService = { historyId, historyTitle ->
                        navHostController.navigate(
                            Screen.DetailHistoryService.createRoute(
                                historyId,
                                historyTitle
                            )
                        )
                    },
                )
            }

            composable(route = Screen.Consultation.route) {
                ConsultationScreen(

                    navigateBack = {
                        navHostController.navigate(Screen.Home.route)
                    }
                )
            }

            composable(route = Screen.Setting.route) {
                SettingScreen(
                    navigateToProfile = { navHostController.navigate(Screen.UserProfile.route) },
                    navigateToStoreProfile = { navHostController.navigate(Screen.StoreProfile.route) },
                    navigateToFaQ = { url ->
                        navHostController.navigate(Screen.WebViewScreen.createRoute(url))
                    },
                    navigateToTnC = { url ->
                        navHostController.navigate(Screen.WebViewScreen.createRoute(url))
                    },
                    navigateToThriveInWeb = { url ->
                        navHostController.navigate(Screen.WebViewScreen.createRoute(url))
                    },
                    navHostController = navHostController,
                )
            }

            composable(route = Screen.StoreProfile.route) {
                StoreProfileScreen(
                    navigateBack = {
                        navHostController.navigateUp()
                    }
                )
            }

            composable(route = Screen.UserProfile.route) {
                UserProfileScreen(
                    navigateBack = {
                        navHostController.navigateUp()
                    }
                )
            }

//        List
            composable(route = Screen.ListArticle.route) {
                ListArticleScreen(navigateBack = {
                    navHostController.navigateUp()
                }, navigateToDetailArticle = { articleId, articleTitle ->
                    navHostController.navigate(
                        Screen.DetailArticle.createRoute(
                            articleId,
                            articleTitle
                        )
                    )
                })
            }

            composable(
                route = Screen.ListService.route,
                arguments = listOf(
                    navArgument("serviceCategoryId") { type = NavType.StringType },
                    navArgument("serviceTitle") { type = NavType.StringType }
                )
            ) {
                val id = it.arguments?.getString("serviceCategoryId") ?: ""
                val title = it.arguments?.getString("serviceTitle") ?: ""

                ListServiceScreen(
                    id = id,
                    title = title,
                    navigateBack = {
                        navHostController.navigateUp()
                    },
                    navigateToDetailService = { serviceId, titleService ->
                        navHostController.navigate(
                            Screen.DetailService.createRoute(
                                serviceId,
                                titleService
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.WaitingListService.route,
            ) {

                WaitingListServiceScreen(
                    navigateBack = {
                        navHostController.navigateUp()
                    },
                    navigateToDetailWaitingList = { detailWaitingListId, detailWaitingListTitle ->
                        navHostController.navigate(
                            Screen.DetailWaitingList.createRoute(
                                detailWaitingListId,
                                detailWaitingListTitle
                            )
                        )
                    }
                )
            }

//        Detail
            composable(
                route = Screen.DetailArticle.route,
                arguments = listOf(
                    navArgument("articleId") { type = NavType.StringType },
                    navArgument("articleTitle") { type = NavType.StringType },
                )
            ) {
                val id = it.arguments?.getString("articleId") ?: ""
                val title = it.arguments?.getString("articleTitle") ?: ""

                DetailArticleScreen(id = id, title = title, navigateBack = {
                    navHostController.navigateUp()
                })
            }

            composable(
                route = Screen.DetailService.route,
                arguments = listOf(
                    navArgument("serviceId") { type = NavType.StringType },
                    navArgument("title") { type = NavType.StringType },
                )
            ) {
                val id = it.arguments?.getString("serviceId") ?: ""
                val title = it.arguments?.getString("title") ?: ""

                DetailServiceScreen(
                    id = id,
                    title = title,
                    navigateBack = {
                        navHostController.navigateUp()
                    },
                    navigateToConsultService = { serviceId, serviceTitle ->
                        navHostController.navigate(
                            Screen.DetailConsultService.createRoute(
                                serviceId,
                                serviceTitle
                            )
                        )
                    },
                    navigateToAllDisplayImage = { displayId ->
                        navHostController.navigate(
                            Screen.AllDisplayImage.createRoute(
                                displayId
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.DetailConsultService.route,
                arguments = listOf(
                    navArgument("serviceId") { type = NavType.StringType },
                    navArgument("serviceTitle") { type = NavType.StringType }
                )
            ) {
                val id = it.arguments?.getString("serviceId") ?: ""
                val title = it.arguments?.getString("serviceTitle") ?: ""

                DetailConsultServiceScreen(
                    title = title,
                    id = id,
                    navigateBack = {
                        navHostController.navigateUp()
                    },
                    navigateToTransaction = { transactionId, serviceTitle ->
                        navHostController.navigate(
                            Screen.DetailTransactionService.createRoute(
                                transactionId, serviceTitle
                            )
                        )
                    }
                )
            }

            composable(
                route = Screen.DetailTransactionService.route,
                arguments = listOf(
                    navArgument("transactionId") { type = NavType.StringType },
                    navArgument("serviceTitle") { type = NavType.StringType },
                )
            ) {
                val id = it.arguments?.getString("transactionId") ?: ""
                val serviceTitle = it.arguments?.getString("serviceTitle") ?: ""

                DetailTransactionServiceScreen(
                    id = id,
                    title = serviceTitle,
                    navigateBack = {
                        navHostController.navigateUp()
                    },
                    navigateToWaitingList = {
                        navHostController.navigate(Screen.WaitingListService.route) {
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    navigateToHistoryService = {
                        navHostController.navigate(Screen.History.route) {
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(
                route = Screen.DetailHistoryService.route,
                arguments = listOf(
                    navArgument("historyId") { type = NavType.StringType },
                    navArgument("historyTitle") { type = NavType.StringType },
                )
            ) {
                val id = it.arguments?.getString("historyId") ?: ""
                val title = it.arguments?.getString("historyTitle") ?: ""

                DetailHistoryServiceScreen(
                    id = id,
                    title = title,
                    navigateBack = {
                        navHostController.navigateUp()
                    },
                    navigateToConsultService = { serviceId, serviceTitle ->
                        navHostController.navigate(
                            Screen.DetailConsultService.createRoute(
                                serviceId,
                                serviceTitle
                            )
                        )
                    },
                )
            }

            composable(
                route = Screen.DetailConsultHistoryService.route,
                arguments = listOf(navArgument("consultHistoryId") { type = NavType.StringType })
            ) {
                val id = it.arguments?.getString("consultHistoryId") ?: ""

                DetailConsultHistoryServiceScreen(
                    id = id,
                    navigateBack = { navHostController.navigateUp() },
                )
            }

            composable(
                route = Screen.DetailWaitingList.route,
                arguments = listOf(
                    navArgument("detailWaitingListId") { type = NavType.StringType },
                    navArgument("detailWaitingListTitle") { type = NavType.StringType },
                )
            ) {
                val id = it.arguments?.getString("detailWaitingListId") ?: ""
                val title = it.arguments?.getString("detailWaitingListTitle") ?: ""

                DetailWaitingListScreen(
                    id = id,
                    title = title,
                    navigateBack = { navHostController.navigateUp() },
                    navigateToConsultService = { serviceId, serviceTitle ->
                        navHostController.navigate(
                            Screen.DetailConsultService.createRoute(
                                serviceId,
                                serviceTitle
                            )
                        )
                    },
                    navigateToHistoryService = {
                        navHostController.navigate(Screen.History.route) {
                            popUpTo(navHostController.graph.startDestinationId) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(
                route = Screen.AllDisplayImage.route,
                arguments = listOf(
                    navArgument("allDisplayImage") { type = NavType.StringType },
                )
            ) {
                val id = it.arguments?.getString("allDisplayImage") ?: ""
                AllDisplayImageScreen(
                    id = id,
                    navigateBack = { navHostController.navigateUp() }
                )
            }

            composable(
                route = Screen.WebViewScreen.route,
                arguments = listOf(navArgument("url") { type = NavType.StringType })
            ) {

                val url = it.arguments?.getString("url") ?: ""

                WebViewScreen(url = url)
            }
        }
    }


}
