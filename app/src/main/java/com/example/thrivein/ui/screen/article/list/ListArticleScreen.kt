package com.example.thrivein.ui.screen.article.list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.R
import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.ArticleItem
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListArticleScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetailArticle: (id: String, title: String) -> Unit,
) {

    val listArticleViewModel = hiltViewModel<ListArticleViewModel>()
    val context = LocalContext.current
    var articles: ArticlesResponse? = null

    listArticleViewModel.uiListArticleState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                listArticleViewModel.getAllArticles(articleRequest = ArticleRequest(10, 1))
            }

            is UiState.Success -> {
                articles = uiState.data

            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Scaffold(
        topBar = {
            DetailTopBar(title = stringResource(R.string.news), navigateBack = navigateBack)
        },
        containerColor = Background,
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(items = articles?.articles ?: arrayListOf(), key = { it?.articleId ?: "" }) {
                ArticleItem(
                    id = it?.articleId ?: "",
                    title = it?.title ?: "",
                    content = it?.content ?: "",
                    bannerUrl = it?.bannerUrl ?: "",
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .background(Background)
                        .clickable { navigateToDetailArticle(it?.articleId ?: "", it?.title ?: "") }
                )
            }
        }

    }
}

@Preview
@Composable
fun ListArticleScreenPreview(
) {
    ListArticleScreen(
        modifier = Modifier,
        navigateBack = {},
        navigateToDetailArticle = { id, title -> },
    )
}