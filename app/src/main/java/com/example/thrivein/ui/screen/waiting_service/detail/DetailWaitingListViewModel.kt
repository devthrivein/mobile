package com.example.thrivein.ui.screen.waiting_service.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.request.OrderRequest
import com.example.thrivein.data.network.response.order.OrderResponse
import com.example.thrivein.data.network.response.service.orderPackage.OrderPackageResponse
import com.example.thrivein.data.network.response.waiting.DetailWaitingServiceResponse
import com.example.thrivein.data.repository.order.OrderRepository
import com.example.thrivein.data.repository.waiting.WaitingRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailWaitingListViewModel @Inject constructor(
    private val waitingRepository: WaitingRepository,
    private val orderRepository: OrderRepository,
) : ViewModel() {

    private val _uiDetailWaitingServiceState: MutableStateFlow<UiState<DetailWaitingServiceResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiDetailWaitingServiceState: StateFlow<UiState<DetailWaitingServiceResponse>>
        get() = _uiDetailWaitingServiceState


    private val _uiOrderPackageState: MutableStateFlow<UiState<OrderPackageResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiOrderPackageState: StateFlow<UiState<OrderPackageResponse>>
        get() = _uiOrderPackageState


    private val _uiOrderNowState: MutableStateFlow<UiState<OrderResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiOrderNowState: StateFlow<UiState<OrderResponse>>
        get() = _uiOrderNowState

    fun getWaitingOrderById(orderId: String) {
        viewModelScope.launch {
            waitingRepository.getWaitingOrderById(orderId)
                .catch {
                    _uiDetailWaitingServiceState.value = UiState.Error(it.message.toString())
                }
                .collect { histories ->
                    _uiDetailWaitingServiceState.value = UiState.Success(histories)
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

    fun orderNow(orderRequest: OrderRequest) {
        viewModelScope.launch {
            orderRepository.orderNow(orderRequest).catch {
                _uiOrderNowState.value = UiState.Error(it.message.toString())
            }
                .collect { order ->
                    _uiOrderNowState.value = UiState.Success(order)
                }
        }
    }

}