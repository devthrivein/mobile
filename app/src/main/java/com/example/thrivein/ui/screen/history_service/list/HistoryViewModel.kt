package com.example.thrivein.ui.screen.history_service.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.history.DetailHistoryServiceResponse
import com.example.thrivein.data.network.response.history.HistoryResponse
import com.example.thrivein.data.network.response.service.orderPackage.OrderPackageResponse
import com.example.thrivein.data.repository.history.HistoryRepository
import com.example.thrivein.data.repository.order.OrderRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val orderRepository: OrderRepository,
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

    private val _uiOrderPackageState: MutableStateFlow<UiState<OrderPackageResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiOrderPackageState: StateFlow<UiState<OrderPackageResponse>>
        get() = _uiOrderPackageState

    fun getAllHistoryService() {
        viewModelScope.launch {
            historyRepository.getAllHistoryService()
                .catch {
                    _uiThriveInHistoryServiceState.value = UiState.Error(it.message.toString())
                }
                .collect { histories ->
                    _uiThriveInHistoryServiceState.value = UiState.Success(histories)
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

    fun getOrderPackageByServiceId(serviceId: String) {
        viewModelScope.launch {
            orderRepository.getOrderPackageServiceId(serviceId).catch {
                _uiOrderPackageState.value = UiState.Error(it.message.toString())
            }
                .collect { orderPackage ->
                    _uiOrderPackageState.value = UiState.Success(orderPackage)
                }
        }
    }
}