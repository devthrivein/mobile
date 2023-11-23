package com.example.thrivein.ui.screen.article

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.R
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.ArticleItem
import com.example.thrivein.ui.theme.Background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListArticleScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetailArticle: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            DetailTopBar(title = stringResource(R.string.news), navigateBack = navigateBack)
        },
        containerColor = Background,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(count = 10) {
                ArticleItem(
                    id = it.toString(),
                    title = "Article Lorem $it",
                    content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    bannerUrl = "https://th.bing.com/th/id/OIP.TZGQs7bZPN6z3EIXrkJovQHaE8?rs=1&pid=ImgDetMain",
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .background(Background)
                        .clickable { navigateToDetailArticle(it.toString()) }
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
        navigateToDetailArticle = {},
    )
}