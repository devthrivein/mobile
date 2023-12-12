package com.example.thrivein.ui.screen.storeScanner

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.scan.ScanStoreResponse
import com.example.thrivein.data.repository.scan.ScanRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class StoreScannerViewModel @Inject constructor(
    private val scanRepository: ScanRepository,
) : ViewModel() {

    private val _uiScanStoreState: MutableStateFlow<UiState<ScanStoreResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiScanStoreState: StateFlow<UiState<ScanStoreResponse>>
        get() = _uiScanStoreState


    fun predictStore(
        image: MultipartBody.Part,
    ) {
        viewModelScope.launch {
            scanRepository.predictStore(image)
                .catch {
                    _uiScanStoreState.value = UiState.Error(it.message.toString())
                }
                .collect { scanResult ->
                    _uiScanStoreState.value = UiState.Success(scanResult)
                }
        }
    }

}
