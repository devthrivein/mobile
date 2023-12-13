package com.example.thrivein.ui.screen.service.detail.detailService

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.thrivein.R
import com.example.thrivein.data.network.response.service.ServiceResponse
import com.example.thrivein.data.network.response.service.portfolio.PortfolioResponse
import com.example.thrivein.ui.component.ShimmerBrush
import com.example.thrivein.ui.component.button.SeeAllButton
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState
import com.example.thrivein.utils.toRpString
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailServiceScreen(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    navigateBack: () -> Unit,
    navigateToConsultService: (id: String, title: String) -> Unit,
    detailServiceViewModel: DetailServiceViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    var servicesResponse: ServiceResponse? = null
    var portfolioResponse: PortfolioResponse? = null
    var isLoading by remember { mutableStateOf(false) }
    var refreshState by remember { mutableStateOf(false) }

    detailServiceViewModel.uiServiceResponseState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                detailServiceViewModel.getServiceById(id)
            }

            is UiState.Success -> {
                isLoading = false
                servicesResponse = uiState.data

            }

            is UiState.Error -> {
                isLoading = false
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

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

            else -> {}
        }
    }

    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack)
        },
        floatingActionButton = {
            ThriveInButton(
                onClick = { navigateToConsultService(id, title) },
                label = stringResource(R.string.consult),
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 24.dp),
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        containerColor = Background,
    ) { innerPadding ->
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshState),
            onRefresh = {
                refreshState = true
                isLoading = true
                detailServiceViewModel.getServiceById(id)
                detailServiceViewModel.getPortfolioByServiceId(id, 10, 1)
                refreshState = false
                isLoading = false
            }) {
            LazyColumn(
                modifier = modifier.padding(innerPadding),
                userScrollEnabled = true,
            ) {
                item {
                    AsyncImage(
                        model = servicesResponse?.iconUrl,
                        contentDescription = title,
                        modifier = Modifier
                            .height(166.dp)
                            .fillMaxWidth()
                            .background(ShimmerBrush(targetValue = 1300f, showShimmer = isLoading)),
                        contentScale = ContentScale.Crop,
                    )
                }

                item {
                    Text(
                        text = servicesResponse?.price?.toRpString() ?: "-",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier
                            .padding(24.dp)
                            .background(ShimmerBrush(targetValue = 1300f, showShimmer = isLoading))
                    )
                }

                item {
                    Text(
                        text = servicesResponse?.description ?: "",
                        style = MaterialTheme.typography.bodySmall, modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .background(
                                ShimmerBrush(
                                    targetValue = 1300f,
                                    showShimmer = isLoading
                                )
                            )
                    )

                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    SeeAllButton(label = stringResource(R.string.portfolio), onClickButton = {})
                }

                item {
                    LazyHorizontalGrid(
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                            .padding(
                                horizontal = 24.dp,
                                vertical = 16.dp
                            ),
                        rows = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        userScrollEnabled = false,
                    ) {
                        items(
                            items = portfolioResponse?.portfolio ?: arrayListOf()
                        ) {
                            AsyncImage(
                                model = it?.imageUrl ?: "",
                                contentDescription = title,
                                modifier = Modifier
                                    .width(160.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(
                                        ShimmerBrush(
                                            targetValue = 1300f,
                                            showShimmer = isLoading
                                        )
                                    ),
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailServiceScreenPreview() {
    DetailServiceScreen(
        id = "1",
        title = "Social Media Management",
        navigateBack = {},
        navigateToConsultService = { id, title -> },
        detailServiceViewModel = hiltViewModel(),
    )
}