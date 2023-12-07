package com.example.thrivein.ui.component.slider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.data.network.response.banner.BannerResponse
import com.example.thrivein.ui.theme.Primary
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerSlider(
    modifier: Modifier = Modifier,
    listBanner: BannerResponse?
) {

    val pagerState = rememberPagerState(initialPage = 0)

    LaunchedEffect(Unit) {
        while (true) {
            yield()
            delay(2600)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
        }
    }

    Column {
        HorizontalPager(
            count = listBanner?.banners?.size ?: 0,
            state = pagerState,
            userScrollEnabled = true,
            itemSpacing = 16.dp,
            modifier = modifier
                .height(160.dp)
                .fillMaxWidth()
        ) { page ->
            val banner = listBanner?.banners?.getOrNull(page)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Primary)
            ) {
                banner?.bannerUrl?.let { imageUrl ->
                    AsyncImage(
                        model = banner.bannerUrl,
                        contentDescription = banner.title ?: "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }

            }
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun BannerSliderPreview() {
    BannerSlider(listBanner = BannerResponse())
}