package com.example.thrivein.ui.screen.listArticle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thrivein.ui.component.card.ListArticleCard
import com.example.thrivein.ui.theme.Background

@Composable
fun DetailArticleScreen(
    modifier: Modifier = Modifier
) {
}

@Composable
fun DetailArticleContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(horizontal = 30.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ListArticleCard(
        )
        ListArticleCard(
        )
    }

}

@Preview
@Composable
fun DetailArticlePreview(
) {
    DetailArticleContent()
}