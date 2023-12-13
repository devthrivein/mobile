package com.example.thrivein.ui.screen.service.detail.detailTransactionService

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.network.response.service.orderPackage.OrderPackageResponse
import com.example.thrivein.ui.component.button.ThriveInButton
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.PackageItem
import com.example.thrivein.ui.component.item.TransactionMetaDataItem
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.ui.theme.Primary
import com.example.thrivein.utils.UiState
import com.example.thrivein.utils.toRpString

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailTransactionServiceScreen(
    id: String,
    title: String,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToHistoryService: () -> Unit,
    detailTransactionServiceViewModel: DetailTransactionServiceViewModel = hiltViewModel(),
    authViewModel: AuthViewModel = hiltViewModel(),
) {
    val items = listOf<String>("Transfer Bank", "COD", "Check")
    val user by authViewModel.getUser().observeAsState()
    val store by authViewModel.getStore().observeAsState()
    val context = LocalContext.current
    var orderPackageResponse: OrderPackageResponse? = null
    var totalOrder: Int = 0

    detailTransactionServiceViewModel.uiOrderPackageState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {

                detailTransactionServiceViewModel.getOrderPackageByServiceId(id)
            }

            is UiState.Success -> {
                orderPackageResponse = uiState.data


            }

            is UiState.Error -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.White)
    ) {
        val scaffoldState = rememberBottomSheetScaffoldState(

        )
        BottomSheetScaffold(
            topBar = {
                DetailTopBar(title = title, navigateBack = navigateBack)
            },

            scaffoldState = scaffoldState,
            sheetContainerColor = Background,
            sheetContent = {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, bottom = 32.dp)
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
                            items = items,
                            onSelected = {})
                        Spacer(modifier = Modifier.height(6.dp))
                        PaymentDetailItem(
                            label = stringResource(id = R.string.address),
                            valueString = store?.address ?: "-"
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        PaymentDetailItem(
                            label = stringResource(R.string.total_order),
                            valueString = totalOrder.toRpString(),
                        )
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    Divider(color = Primary)
                    Spacer(modifier = Modifier.height(18.dp))
                    PaymentDetailItem(
                        isImportant = true,
                        label = stringResource(R.string.total),
                        valueString = totalOrder.toRpString(),
                        modifier = Modifier.padding(horizontal = 48.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        ThriveInButton(
                            onClick = { },
                            label = stringResource(R.string.order_later),
                            isOutline = true,
                            isNotWide = true
                        )
                        ThriveInButton(
                            onClick = { navigateToHistoryService() },
                            label = stringResource(R.string.order_now),
                            isNotWide = true,
                        )
                    }
                }
            },
        ) {

            Column(
                modifier = modifier.padding()
            ) {
                TransactionMetaDataItem()

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
                                text = stringResource(R.string.detail_package),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }

                    items(items = orderPackageResponse?.item ?: arrayListOf()) {

                        totalOrder += (it?.price ?: 0)

                        PackageItem(
                            title = it?.title ?: "",
                            qty = it?.qty ?: 0,
                            price = it?.price ?: 0,
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


@Preview(showBackground = true)
@Composable
fun DetailTransactionServiceScreenPreview() {
    DetailTransactionServiceScreen(
        id = "1",
        title = "test",
        navigateToHistoryService = {},
        navigateBack = {})
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

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var selectedItem by remember { mutableStateOf("Select") }

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
                modifier = Modifier
                    .width((screenWidth.value * 0.3).dp),
            )
        } else if (items.isNotEmpty() && valueString == "") {
            Box(
                modifier = Modifier
                    .width((screenWidth.value * 0.3).dp)
                    .clickable {
                        isExpanded = !isExpanded
                    }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedItem,
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Icon(
                        painter = painterResource(id = if (isExpanded) R.drawable.arrow_up else R.drawable.arrow_down),
                        contentDescription = ""

                    )
                }

                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = { isExpanded = false }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedItem = item
                                isExpanded = false
                                onSelected(selectedItem)

                            }
                        )
                    }
                }
            }
        } else {

        }

    }
}


@Preview(showBackground = true)
@Composable
fun PaymentDetailItemPreview() {
    PaymentDetailItem(label = stringResource(R.string.payment_method), items = listOf("Gojek"))
}