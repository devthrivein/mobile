package com.example.thrivein.ui.screen.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.ui.theme.Background
import java.util.Date

@Composable
fun DetailArticleScreen(
    modifier: Modifier = Modifier,
    id: String,
    navigateBack: () -> Unit,
) {
//    Scaffold(
//        topBar = {
//            DetailTopBar(title = id, navigateBack = navigateBack)
//        },
//    ) {
//
//    }
//    Box(
//        modifier = modifier
//            .background(Background)
//            .fillMaxWidth()
//    ) {
//        AsyncImage(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(400.dp),
//            model = bannerUrl,
//            contentDescription = title,
//            contentScale = ContentScale.Crop,
//            alignment = Alignment.TopStart,
//            placeholder = painterResource(id = R.drawable.ic_result_photo)
//        )
//        Spacer(modifier = Modifier.height(400.dp))
//        LazyColumn(
//            modifier = Modifier
//                .background(Color.White)
//                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
//                .padding(horizontal = 24.dp, vertical = 12.dp)
//                .fillMaxSize()
//                .align(Alignment.BottomEnd),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            items(count = 1) {
//                Text(text = title)
//                Text(text = content)
//            }
//        }
//    }
}

@Preview
@Composable
fun ParallaxTheme() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Parallax(
            id = "1",
            title = "Detail",
            content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            bannerUrl = "https://th.bing.com/th/id/OIP.TZGQs7bZPN6z3EIXrkJovQHaE8?rs=1&pid=ImgDetMain",
            uploadedDate = Date(2023, 1, 7, 10, 0),
        ) {
        }
    }
}

@Composable
fun Parallax(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    content: String,
    bannerUrl: String,
    uploadedDate: Date,
    navigateBack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Box(
        modifier = modifier
            .background(Background)
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
                        alpha = 1f - (scrollState.value.toFloat() / scrollState.maxValue / 2)
                        translationY = 0.5f * scrollState.value
                    },
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(400.dp)
                        .align(Alignment.TopCenter),
                    model = bannerUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopStart,
                    placeholder = painterResource(id = R.drawable.ic_not_found)
                )
//                Divider(Modifier.padding(10.dp))
            }
            Card(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .padding(horizontal = 12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 20.dp)
//                        .background(Color.White),
                ) {
                    Text(
//                    modifier = Modifier.padding(8.dp),
                        text = title,
                        style = MaterialTheme.typography.headlineMedium.copy(

                        )
                    )
                    Text(
                        modifier = Modifier.padding(top = 10.dp, bottom = 40.dp),
                        text = content,
                        style = MaterialTheme.typography.bodySmall.copy(
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


@Preview
@Composable
fun ParallaxPreview() {
    Parallax(
        modifier = Modifier,
        id = "1",
        title = "Detail",
        content = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
        bannerUrl = "https://th.bing.com/th/id/OIP.TZGQs7bZPN6z3EIXrkJovQHaE8?rs=1&pid=ImgDetMain",
        uploadedDate = Date(2023, 1, 7, 10, 0),
        navigateBack = {}
    )
}

//@Preview
@Composable
fun DetailArticleScreenPreview() {
    DetailArticleScreen(
        modifier = Modifier,
        id = "1",
        navigateBack = {}
    )
}