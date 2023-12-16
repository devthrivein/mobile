package com.example.thrivein.ui.screen.history_service.detail


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.R
import com.example.thrivein.data.network.response.history.DetailHistoryServiceResponse
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.HistoryMetaDataItem
import com.example.thrivein.ui.component.item.PackageItem
import com.example.thrivein.ui.screen.history_service.list.HistoryViewModel
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary
import com.example.thrivein.utils.UiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

@Composable
fun DetailHistoryServiceScreen(
    id: String,
    title: String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToConsultService: (serviceId: String, serviceTitle: String) -> Unit,
    historyViewModel: HistoryViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var detailHistoryService: DetailHistoryServiceResponse? = null
    var isLoading by remember { mutableStateOf(false) }
    var refreshState by remember { mutableStateOf(false) }

    historyViewModel.uiThriveInDetailHistoryServiceState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                refreshState = true
                historyViewModel.getDetailHistoryById(id)
            }

            is UiState.Success -> {
                isLoading = false
                refreshState = false
                detailHistoryService = uiState.data
            }

            is UiState.Error -> {
                isLoading = false
                refreshState = false
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack, actions = {
                IconButton(onClick = {
                    navigateToConsultService(id, title)
                }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_consultation),
                            contentDescription = stringResource(id = R.string.consultation),
                        )
                    }
                }
            })
        },
    ) {}
    Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
        ) {
            val scaffoldState = rememberBottomSheetScaffoldState()
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetContainerColor = Background,
                sheetContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp, bottom = 32.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 48.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.payment_details),
                                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            PaymentDetailItem(
                                label = stringResource(R.string.payment_method),
                                valueString = "COD",
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            PaymentDetailItem(
                                label = stringResource(id = R.string.address),
                                valueString = "Jalan Tangkuban Perahu"
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            PaymentDetailItem(
                                label = stringResource(R.string.total_order),
                                valueString = "Rp 300000"
                            )
                        }
                        Spacer(modifier = Modifier.height(18.dp))
                        Divider(color = Primary)
                        Spacer(modifier = Modifier.height(18.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 48.dp)
                        ) {
                            PaymentDetailItem(
                                isImportant = true,
                                label = stringResource(R.string.total),
                                valueString = "Rp 300000",
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                ThriveInButton(
                                    onClick = { },
                                    label = stringResource(R.string.rate),
                                    isOutline = true,
                                    isNotWide = true,
                                    modifier = Modifier
                                        .fillMaxWidth(0.47f)
                                )
                                Spacer(modifier = Modifier.width(20.dp))
                                ThriveInButton(
                                    onClick = { },
                                    label = stringResource(R.string.re_order),
                                    isNotWide = true,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                },
            ) {
                Column(
                    modifier = modifier.padding()
                ) {
//            if (isLoading) {
//                ThriveInLoading()
//            } else {
//                SwipeRefresh(
//                    state = rememberSwipeRefreshState(isRefreshing = refreshState),
//                    onRefresh = {
//                        refreshState = true
//                        isLoading = true
//                        historyViewModel.getDetailHistoryById(id)
//                        refreshState = false
//                        isLoading = false
//                    }) {
                    HistoryMetaDataItem()

                    LazyColumn(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        stickyHeader {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 16.dp)
                                    .background(
                                        Background
                                    )
                            ) {
                                Text(
                                    text = stringResource(R.string.order_detail),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }

                        items(count = 10) {
                            PackageItem(
                                title = "Lorem Ipsum 1x${it + 1}",
                                qty = it,
                                price = (it + 1) * 100000,
                                bannerUrl = stringResource(
                                    id = R.string.dummy_image
                                ),
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                    }
                }
            }
        }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailHistoryServiceScreenPreview() {
    DetailHistoryServiceScreen(
        id = "1",
        title = "",
        navigateBack = {},
        navigateToConsultService = { serviceId, serviceTitle -> })
}


@Composable
fun PaymentDetailItem(
    modifier: Modifier = Modifier,
    label: String,
    isImportant: Boolean = false,
    valueString: String = "",
    items: List<String> = arrayListOf(),
    onSelected: (String) -> Unit = {},
) {

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = if (isImportant) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodySmall
        )
        if (items.isEmpty() && valueString != "") {
            Text(
                text = valueString,
                style = if (isImportant) MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold) else MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Right,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.width((screenWidth.value * 0.3).dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PaymentDetailItemPreview() {
    PaymentDetailItem(
        label = stringResource(R.string.payment_method), items = listOf("Gojek")
    )
}