package com.example.thrivein.ui.screen.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.network.response.banner.BannerResponse
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.ui.component.button.SeeAllButton
import com.example.thrivein.ui.component.grid.HomeGridServiceCategoryView
import com.example.thrivein.ui.component.header.HomeHeader
import com.example.thrivein.ui.component.item.ArticleHomeItem
import com.example.thrivein.ui.component.slider.BannerSlider
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToListService: (id: String, title: String) -> Unit,
    navigateToScanStore: () -> Unit,
    navigateToListArticle: () -> Unit,
    navigateToDetailArticle: (id: String, title: String) -> Unit,
    navigateToWaitingList: () -> Unit,
    navigateToBanner: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var serviceCategories: ServiceCategoriesResponse? by remember {
        mutableStateOf(null)

    }
    var articles: ArticlesResponse? by remember {
        mutableStateOf(null)

    }
    var banners: BannerResponse? by remember {
        mutableStateOf(null)

    }
    val user by authViewModel.getUser().observeAsState()

    var refreshState by remember { mutableStateOf(false) }

    homeViewModel.uiListThriveInServiceCategoryState.collectAsState(
        initial = UiState.Loading
    ).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {

                if (user?.token.toString() != "null") {
                    homeViewModel.getAllServiceCategory()

                }

            }

            is UiState.Success -> {
                serviceCategories = uiState.data

            }

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    uiState.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {}
        }
    }

    homeViewModel.uiListArticleState.collectAsState(
        initial = UiState.Loading
    ).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                if (user?.token.toString() != "null") {
                    homeViewModel.getAllArticles(
                        articleRequest = ArticleRequest(
                            5,
                            1
                        )
                    )
                }

            }

            is UiState.Success -> {
                articles = uiState.data

            }

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    uiState.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    homeViewModel.uiThriveInBannerState.collectAsState(
        initial = UiState.Loading
    ).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                refreshState = true
                if (user?.token.toString() != "null") {
                    homeViewModel.getAllBannerSlider()
                }
            }

            is UiState.Success -> {
                refreshState = false
                banners = uiState.data
            }

            is UiState.Error -> {
                refreshState = false
                Toast.makeText(
                    context,
                    uiState.errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }



    Scaffold(
        containerColor = Background,
    ) { innerPadding ->

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshState),
            onRefresh = {
                refreshState = true

                homeViewModel.getAllServiceCategory()
                homeViewModel.getAllArticles(
                    articleRequest = ArticleRequest(
                        5,
                        1
                    )
                )
                homeViewModel.getAllBannerSlider()

                refreshState = false

            }) {
            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                item {
                    HomeHeader(
                        username = user?.name ?: "-",
                        navigateToWaitingList = navigateToWaitingList,
                        modifier = Modifier.padding(24.dp)
                    )
                }
                item {
                    BannerSlider(
                        listBanner = banners,
                        isLoading = refreshState,
                        navigateToBanner = navigateToBanner,
                        modifier = Modifier
                            .padding(horizontal = 24.dp)

                    )
                }

                item {
                    HomeGridServiceCategoryView(
                        listCategory = serviceCategories,
                        navigateToListService = { id, title ->
                            navigateToListService(id, title)
                        },
                        navigateToScanStore = navigateToScanStore,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                }

                item {
                    SeeAllButton(
                        label = stringResource(R.string.today_s_news),
                        onClickButton = navigateToListArticle
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                }
                items(items = articles?.articles.orEmpty(), key = { it?.articleId.orEmpty() }) {
                    ArticleHomeItem(
                        id = it?.articleId ?: "",
                        title = it?.title ?: "",
                        content = it?.content ?: "",
                        bannerUrl = it?.bannerUrl ?: "",
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 24.dp)
                            .clickable {
                                navigateToDetailArticle(it?.articleId ?: "", it?.title ?: "")
                            }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToListService = { id, title ->
        },
        navigateToDetailArticle = { id, title -> },
        navigateToListArticle = {},
        navigateToScanStore = {},
        navigateToWaitingList = {},
        navigateToBanner = {}
    )
}