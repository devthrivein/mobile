package com.example.thrivein.ui.screen.history_service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.history.DetailHistoryServiceResponse
import com.example.thrivein.data.network.response.history.HistoryResponse
import com.example.thrivein.data.repository.history.HistoryRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository
) : ViewModel() {
    //HISTORY SERVICE
    private val _uiThriveInHistoryServiceState: MutableStateFlow<UiState<HistoryResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiThriveInHistoryServiceState: StateFlow<UiState<HistoryResponse>>
        get() = _uiThriveInHistoryServiceState

    //DETAIL HISTORY SERVICE
    private val _uiThriveInDetailHistoryServiceState: MutableStateFlow<UiState<DetailHistoryServiceResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiThriveInDetailHistoryServiceState: StateFlow<UiState<DetailHistoryServiceResponse>>
        get() = _uiThriveInDetailHistoryServiceState

    fun getAllHistoryService() {
        viewModelScope.launch {
            historyRepository.getAllHistoryService()
                .catch {
                    _uiThriveInHistoryServiceState.value = UiState.Error(it.message.toString())
                }
                .collect { history ->
                    _uiThriveInHistoryServiceState.value = UiState.Success(history)
                }
        }
    }

    fun getDetailHistoryById(orderId: String) {
        viewModelScope.launch {
            historyRepository.getDetailHistoryById(orderId)
                .catch {
                    _uiThriveInDetailHistoryServiceState.value =
                        UiState.Error(it.message.toString())
                }
                .collect { detailHistoryService ->
                    _uiThriveInDetailHistoryServiceState.value =
                        UiState.Success(detailHistoryService)
                }
        }
    }
}