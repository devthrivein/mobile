package com.example.thrivein.ui.screen.service.detail.detailTransactionService

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.service.orderPackage.OrderPackageResponse
import com.example.thrivein.data.repository.service.ServiceRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTransactionServiceViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository,
) : ViewModel() {

    private val _uiOrderPackageState: MutableStateFlow<UiState<OrderPackageResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiOrderPackageState: StateFlow<UiState<OrderPackageResponse>>
        get() = _uiOrderPackageState

    fun getOrderPackageByServiceId(serviceId: String) {
        viewModelScope.launch {
            serviceRepository.getOrderPackageServiceId(serviceId).catch {
                _uiOrderPackageState.value = UiState.Error(it.message.toString())
            }
                .collect { orderPackage ->
                    _uiOrderPackageState.value = UiState.Success(orderPackage)
                }
        }
    }

}