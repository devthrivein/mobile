package com.example.thrivein.ui.screen.service.detail.detailService

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.thrivein.data.network.response.service.portfolio.PortfolioResponse
import com.example.thrivein.ui.component.ShimmerBrush
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.theme.Primary
import com.example.thrivein.utils.UiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllDisplayImageScreen(
    modifier: Modifier = Modifier,
    id: String,
    navigateBack: () -> Unit,
    detailServiceViewModel: DetailServiceViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var portfolioResponse: PortfolioResponse? = null
    var isLoading by remember { mutableStateOf(false) }
    var refreshState by remember { mutableStateOf(false) }

    detailServiceViewModel.uiPortfolioResponseState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                detailServiceViewModel.getPortfolioByServiceId(id, size = 10, page = 1)
            }

            is UiState.Success -> {
                isLoading = false
                portfolioResponse = uiState.data
            }

            is UiState.Error -> {
                isLoading = false
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold(
        topBar = {
            DetailTopBar(title = "", navigateBack = navigateBack)
        },
    ) { innerPadding ->

//        LazyColumn(
//            modifier = modifier.padding(innerPadding),
//            userScrollEnabled = true
//        ) {
//            item {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshState),
            onRefresh = {
                refreshState = true
                isLoading = true
                detailServiceViewModel.getPortfolioByServiceId(id, 10, 1)
                refreshState = false
                isLoading = false
            }) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
//            content ="",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalItemSpacing = 16.dp,
                userScrollEnabled = true
            ) {
                items(
                    items = portfolioResponse?.portfolio ?: arrayListOf()
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(4.dp))
                            .background(
                                ShimmerBrush(
                                    targetValue = 1300f,
                                    showShimmer = isLoading
                                )
                            ),
                        model = it?.imageUrl ?: "",
                        contentDescription = it?.title ?: "",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
//    }
//}

@Preview
@Composable
fun AllDisplayImageScreenPreview() {
    AllDisplayImageScreen(
        navigateBack = {},
        id = "1",
        detailServiceViewModel = hiltViewModel()
    )
}

@Composable
fun Image(
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier
            .background(Primary)
            .clip(shape = RoundedCornerShape(4.dp)),
        model = "https://th.bing.com/th/id/OIP.zwZX_4Qz6c-03jWp37mHzAHaFF?rs=1&pid=ImgDetMain",
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}