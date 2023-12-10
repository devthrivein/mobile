package com.example.thrivein.ui.screen.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.local.model.Article
import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.network.response.banner.BannerResponse
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.ui.component.button.SeeAllButton
import com.example.thrivein.ui.component.grid.HomeGridServiceCategoryView
import com.example.thrivein.ui.component.header.HomeHeader
import com.example.thrivein.ui.component.item.ArticleHomeItem
import com.example.thrivein.ui.component.loading.ThriveInLoading
import com.example.thrivein.ui.component.slider.BannerSlider
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToListService: (id: String, title: String) -> Unit,
    navigateToScanStore: () -> Unit,
    navigateToListArticle: () -> Unit,
    navigateToDetailArticle: (id: String) -> Unit,
    navigateToWaitingList: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var serviceCategories: ServiceCategoriesResponse? = null
//    var articles: List<Article> = arrayListOf<Article>()
    var listArticles: ArticlesResponse? = null
    var banners: BannerResponse? = null

    val user by authViewModel.getUser().observeAsState()

    var isLoadingBanner by remember {
        mutableStateOf(false)
    }


    homeViewModel.uiListThriveInServiceCategoryState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                homeViewModel.getAllServiceCategory()
            }

            is UiState.Success -> {
                serviceCategories = uiState.data

            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

//    homeViewModel.uiListArticleState.collectAsState(initial = UiState.Loading).value.let { uiState ->
//        when (uiState) {
//            is UiState.Loading -> {
//                homeViewModel.getAllArticlesHome(articleRequest = ArticleRequest(3, 1))
////                isLoading= true
//            }
//
//            is UiState.Success -> {
//                listArticles = uiState.data
//
//            }
//
//            is UiState.Error -> {
//                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    homeViewModel.uiThriveInBannerState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoadingBanner = true
                homeViewModel.getAllBannerSlider()
            }

            is UiState.Success -> {
                isLoadingBanner = false
                banners = uiState.data
            }

            is  UiState.Error -> {
                isLoadingBanner = false
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }


    Scaffold(
        containerColor = Background,
    ) { innerPadding ->

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
                if (isLoadingBanner) {
                    CircularProgressIndicator(color = Color.Yellow, modifier = modifier)
                } else {
                BannerSlider(listBanner = banners, modifier = Modifier.padding(horizontal = 24.dp))
                }
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
            items(items = listArticles?.articles.orEmpty(), key = { it?.articleId.orEmpty() }) {
                ArticleHomeItem(
                    id = it?.articleId ?: "",
                    title = it?.title ?: "",
                    content = it?.content ?: "",
                    bannerUrl = it?.bannerUrl ?: "",
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 24.dp)
                        .clickable {
                            navigateToDetailArticle(it?.articleId ?: "")
                        }
                )
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
        navigateToDetailArticle = {},
        navigateToListArticle = {},
        navigateToScanStore = {},
        navigateToWaitingList = {},
    )
}