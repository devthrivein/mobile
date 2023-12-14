package com.example.thrivein.ui.screen.service.detail.detailTransactionService

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.request.OrderRequest
import com.example.thrivein.data.network.response.order.OrderResponse
import com.example.thrivein.data.network.response.service.orderPackage.OrderPackageResponse
import com.example.thrivein.data.repository.order.OrderRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTransactionServiceViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
) : ViewModel() {

    private val _uiOrderPackageState: MutableStateFlow<UiState<OrderPackageResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiOrderPackageState: StateFlow<UiState<OrderPackageResponse>>
        get() = _uiOrderPackageState

    private val _uiOrderNowState: MutableStateFlow<UiState<OrderResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiOrderNowState: StateFlow<UiState<OrderResponse>>
        get() = _uiOrderNowState

    private val _uiOrderLaterState: MutableStateFlow<UiState<OrderResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiOrderLaterState: StateFlow<UiState<OrderResponse>>
        get() = _uiOrderLaterState

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

    fun orderLater(orderRequest: OrderRequest) {
        viewModelScope.launch {
            orderRepository.orderLater(orderRequest).catch {
                _uiOrderLaterState.value = UiState.Error(it.message.toString())
            }
                .collect { order ->
                    _uiOrderLaterState.value = UiState.Success(order)
                }
        }
    }

}