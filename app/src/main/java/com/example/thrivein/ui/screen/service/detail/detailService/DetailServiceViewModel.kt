package com.example.thrivein.ui.screen.service.detail.detailService

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.service.ServiceResponse
import com.example.thrivein.data.network.response.service.portfolio.PortfolioResponse
import com.example.thrivein.data.repository.service.ServiceRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailServiceViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository,
) : ViewModel() {

    //    Service
    private val _uiServiceResponseState: MutableStateFlow<UiState<ServiceResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiServiceResponseState: StateFlow<UiState<ServiceResponse>>
        get() = _uiServiceResponseState

    //    Portfolio
    private val _uiPortfolioResponseState: MutableStateFlow<UiState<PortfolioResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiPortfolioResponseState: StateFlow<UiState<PortfolioResponse>>
        get() = _uiPortfolioResponseState

    fun getServiceById(serviceId: String) {
        viewModelScope.launch {
            serviceRepository.getServiceById(serviceId)
                .catch {
                    _uiServiceResponseState.value = UiState.Error(it.message.toString())
                }
                .collect { service ->
                    _uiServiceResponseState.value = UiState.Success(service)
                }
        }
    }

    fun getPortfolioByServiceId(serviceId: String, size: Int, page: Int) {
        viewModelScope.launch {
            serviceRepository.getPortfolioByServiceId(serviceId, size, page)
                .catch {
                    _uiPortfolioResponseState.value = UiState.Error(it.message.toString())
                }
                .collect { portfolio ->
                    _uiPortfolioResponseState.value = UiState.Success(portfolio)
                }
        }
    }

}