package com.example.thrivein.ui.screen.service.list

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.data.network.response.service.ListServicesResponse
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.ServiceListItem
import com.example.thrivein.ui.component.loading.ThriveInLoading
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListServiceScreen(
    modifier: Modifier = Modifier,
    id: String,
    title: String = "",
    navigateBack: () -> Unit,
    navigateToDetailService: (id: String, title: String) -> Unit,
    listServiceViewModel: ListServiceViewModel = hiltViewModel(),
) {

    val context = LocalContext.current
    var listServicesResponse: ListServicesResponse? = null
    var isLoading by remember { mutableStateOf(false) }
    var refreshState by remember { mutableStateOf(false) }

    listServiceViewModel.uiListServicesState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                isLoading = true
                refreshState = true
                listServiceViewModel.getAllServices(id)
            }

            is UiState.Success -> {
                isLoading = false
                refreshState = false
                listServicesResponse = uiState.data

            }

            is UiState.Error -> {
                isLoading = false
                refreshState = false
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    Scaffold(
        topBar = {
            DetailTopBar(title = title, navigateBack = navigateBack)
        },
        containerColor = Background,
    ) { innerPadding ->

        Box(
            contentAlignment = Alignment.Center
        ) {

            if (isLoading) {
                ThriveInLoading()
            } else {
                SwipeRefresh(
                    state = rememberSwipeRefreshState(isRefreshing = refreshState),
                    onRefresh = {
                        refreshState = true
                        isLoading = true
                        listServiceViewModel.getAllServices(id)
                        refreshState = false
                        isLoading = false
                    }) {
                    LazyColumn(
                        modifier = Modifier.padding(innerPadding),
                        userScrollEnabled = true,
                    ) {
                        items(
                            listServicesResponse?.listServices ?: arrayListOf(),
                            key = { it?.serviceId ?: "" }) {
                            ServiceListItem(
                                id = it?.serviceId ?: "",
                                title = it?.title ?: "",
                                iconUrl = it?.iconUrl ?: "",
                                modifier = Modifier
                                    .padding(horizontal = 24.dp, vertical = 12.dp)
                                    .clickable {
                                        navigateToDetailService(
                                            it?.serviceId ?: "",
                                            it?.title ?: ""
                                        )
                                    },
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
fun ListServiceScreenPreview() {
    ListServiceScreen(
        id = "1",
        title = "Online Solutions",
        navigateBack = {},
        navigateToDetailService = { id, title ->
        },
    )
}