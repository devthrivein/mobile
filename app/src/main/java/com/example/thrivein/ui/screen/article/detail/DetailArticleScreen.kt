package com.example.thrivein.ui.screen.article.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.data.network.response.article.DetailArticleResponse
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState

@Composable
fun DetailArticleScreen(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    navigateBack: () -> Unit,
    detailArticleViewModel: DetailArticleViewModel = hiltViewModel(),

    ) {

    val context = LocalContext.current
    var detailArticleResponse: DetailArticleResponse? = null


    detailArticleViewModel.uiDetailArticleResponseState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                detailArticleViewModel.getDetailArticle(id)
            }

            is UiState.Success -> {
                detailArticleResponse = uiState.data

            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }



    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack)
        },
    ) { innerpadding ->
        val scrollState = rememberScrollState()
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Box(
                modifier = modifier
                    .background(Background)
                    .padding(innerpadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .graphicsLayer {
                                alpha =
                                    1f - (scrollState.value.toFloat() / scrollState.maxValue / 2)
                                translationY = 0.5f * scrollState.value
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(400.dp),
                            model = detailArticleResponse?.bannerUrl ?: "",
                            contentDescription = title,
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(id = R.drawable.ic_not_found)
                        )
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White)

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 20.dp, vertical = 20.dp)
                        ) {
                            Text(
                                text = detailArticleResponse?.title ?: "",
                                style = MaterialTheme.typography.headlineMedium.copy(

                                )
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_profile),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = stringResource(id = R.string.app_name),
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Normal
                                        )
                                    )

                                }
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                ) {
                                    Spacer(modifier = Modifier.width(40.dp))
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_date),
                                        contentDescription = null
                                    )
                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = stringResource(id = R.string.published),
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Normal
                                        )
                                    )
//                                    Spacer(modifier = Modifier.width(3.dp))
                                    Text(
                                        text = detailArticleResponse?.uploadedDate ?: "",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            fontWeight = FontWeight.Normal
                                        ),
                                        textAlign = TextAlign.Right,
                                        )

                                }
                            }
                            Text(
                                modifier = Modifier.padding(
                                    top = 10.dp, bottom = 40.dp),
                                text = detailArticleResponse?.content ?: "",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Normal
                                ),
                                textAlign = TextAlign.Justify,
                                overflow = TextOverflow.Visible,
                            )
                        }
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun DetailArticleScreenPreview() {
    DetailArticleScreen(
        modifier = Modifier,
        id = "1",
        title = "Detail",
        navigateBack = {}
    )
}