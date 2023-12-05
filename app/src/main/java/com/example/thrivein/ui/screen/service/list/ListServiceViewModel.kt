package com.example.thrivein.ui.screen.service.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.service.ListServicesResponse
import com.example.thrivein.data.repository.service.ServiceRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListServiceViewModel @Inject constructor(
    private val serviceRepository: ServiceRepository,
) : ViewModel() {

    private val _uiListServicesState: MutableStateFlow<UiState<ListServicesResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiListServicesState: StateFlow<UiState<ListServicesResponse>>
        get() = _uiListServicesState


    fun getAllServices(id: String) {
        viewModelScope.launch {
            serviceRepository.getAllServices(id)
                .catch {
                    _uiListServicesState.value = UiState.Error(it.message.toString())
                }
                .collect { listServices ->
                    _uiListServicesState.value = UiState.Success(listServices)
                }
        }
    }
}