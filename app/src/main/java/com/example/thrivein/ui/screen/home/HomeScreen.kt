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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thrivein.R
import com.example.thrivein.data.model.Article
import com.example.thrivein.data.model.ThriveInServiceCategory
import com.example.thrivein.ui.component.button.SeeAllButton
import com.example.thrivein.ui.component.grid.HomeGridServiceCategoryView
import com.example.thrivein.ui.component.header.HomeHeader
import com.example.thrivein.ui.component.item.ArticleHomeItem
import com.example.thrivein.ui.component.navigation.BottomBarNavigation
import com.example.thrivein.ui.component.slider.BannerSlider
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigateToListService: (String) -> Unit,
    navigateToScanStore: () -> Unit,
    navigateToListArticle: () -> Unit,
    navigateToDetailArticle: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var serviceCategories: List<ThriveInServiceCategory> = arrayListOf<ThriveInServiceCategory>()
    var articles: List<Article> = arrayListOf<Article>()


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

    homeViewModel.uiListArticleState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                homeViewModel.getAllArticle()
            }

            is UiState.Success -> {
                articles = uiState.data

            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }


    Scaffold(
        bottomBar = {
            BottomBarNavigation(navHostController = navHostController)
        },
        containerColor = Background,
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item {
                HomeHeader(
                    username = "Fika",
                    navigateToWaitingList = {},
                    modifier = Modifier.padding(24.dp)
                )
            }
            item {
                BannerSlider(modifier = Modifier.padding(horizontal = 24.dp))
            }

            item {
                HomeGridServiceCategoryView(
                    listCategory = serviceCategories,
                    navigateToListService = navigateToListService,
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
            items(items = articles, key = { it.id }) { article ->
                ArticleHomeItem(
                    title = article.title,
                    content = article.content,
                    bannerUrl = article.bannerUrl,
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 24.dp)
                        .clickable {
                            navigateToDetailArticle(article.id)
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
        navHostController = rememberNavController(),
        navigateToListService = {},
        navigateToDetailArticle = {},
        navigateToListArticle = {},
        navigateToScanStore = {},
    )
}