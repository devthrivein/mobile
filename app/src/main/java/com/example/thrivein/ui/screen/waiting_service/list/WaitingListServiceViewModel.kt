package com.example.thrivein.ui.screen.waiting_service.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.waiting.WaitingListResponse
import com.example.thrivein.data.repository.waiting.WaitingRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WaitingListServiceViewModel @Inject constructor(
    private val waitingRepository: WaitingRepository,
) : ViewModel() {

    private val _uiWaitingListServiceState: MutableStateFlow<UiState<WaitingListResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiWaitingListServiceState: StateFlow<UiState<WaitingListResponse>>
        get() = _uiWaitingListServiceState


    fun getAllWaitingList() {
        viewModelScope.launch {
            waitingRepository.getAllWaitingOrder()
                .catch {
                    _uiWaitingListServiceState.value = UiState.Error(it.message.toString())
                }
                .collect { histories ->
                    _uiWaitingListServiceState.value = UiState.Success(histories)
                }
        }
    }

}