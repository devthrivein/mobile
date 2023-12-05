package com.example.thrivein.ui.screen.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.request.RegisterRequest
import com.example.thrivein.data.network.response.auth.UserResponse
import com.example.thrivein.data.repository.auth.AuthRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiRegisterState: MutableStateFlow<UiState<UserResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiRegisterState: StateFlow<UiState<UserResponse>>
        get() = _uiRegisterState

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            authRepository.register(registerRequest).catch {
                _uiRegisterState.value = UiState.Error(it.message ?: "")
            }.collect {
                _uiRegisterState.value = UiState.Success(it)
            }
        }
    }


}