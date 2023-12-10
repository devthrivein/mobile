package com.example.thrivein.ui.screen.history_service

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.thrivein.AuthViewModel
import com.example.thrivein.R
import com.example.thrivein.data.network.response.history.HistoryResponse
import com.example.thrivein.ui.screen.history_service.HistoryViewModel
import com.example.thrivein.ui.component.header.DetailTopBar
import com.example.thrivein.ui.component.item.HistoryListItem
import com.example.thrivein.ui.theme.Background
import com.example.thrivein.utils.UiState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryServiceScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    navigateToDetailHistoryService: (id: String) -> Unit,
    historyViewModel: HistoryViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var listHistoryService: HistoryResponse? = null

//    historyViewModel.uiThriveInHistoryServiceState.collectAsState(initial = UiState.Loading).value.let { uiState ->
//        when(uiState) {
//            is UiState.Loading -> {
//                historyViewModel.getAllHistoryService()
//            }
//
//            is UiState.Success -> {
//                listHistoryService = uiState.data
//            }
//
//            is UiState.Error -> {
//                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
//            }
//    }

    Scaffold(
        topBar = {
            DetailTopBar(title = stringResource(id = R.string.history), navigateBack = navigateBack)
        },
        containerColor = Background,
    ) { innerPadding ->

        LazyColumn(
            modifier = modifier.padding(innerPadding)
        ) {
            items(items = listHistoryService?.historyServices.orEmpty(), key = { it?.orderId.orEmpty() }) {
                HistoryListItem(
                    id = it?.orderId ?: "",
                    title = it?.title ?: "",
                    iconUrl = it?.iconUrl ?: "",
                    date = it?.transactionDate ?: "",
                    modifier = Modifier
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .clickable {
                                navigateToDetailHistoryService(it?.orderId ?: "")
                        }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HistoryServiceScreenPreview() {
    HistoryServiceScreen(
        navigateBack = {},
        navigateToDetailHistoryService = {},
    )
}
