package com.example.thrivein.ui.screen.setting.store

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.request.UpdateStoreRequest
import com.example.thrivein.data.network.response.MessageResponse
import com.example.thrivein.data.repository.auth.AuthRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {


    private val _uiMessageResponseState: MutableStateFlow<UiState<MessageResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiMessageResponseState: StateFlow<UiState<MessageResponse>>
        get() = _uiMessageResponseState


    fun updateStore(request: UpdateStoreRequest) {
        viewModelScope.launch {
            authRepository.updateStore(request)
                .catch {
                    _uiMessageResponseState.value = UiState.Error(it.message.toString())
                }
                .collect { message ->
                    _uiMessageResponseState.value = UiState.Success(message)
                }
        }
    }

}