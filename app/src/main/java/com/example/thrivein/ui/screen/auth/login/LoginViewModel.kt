package com.example.thrivein.ui.screen.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.request.LoginRequest
import com.example.thrivein.data.network.response.UserResponse
import com.example.thrivein.data.repository.auth.AuthRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiLoginState: MutableStateFlow<UiState<UserResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiLoginState: StateFlow<UiState<UserResponse>>
        get() = _uiLoginState

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            authRepository.login(loginRequest).catch {
                _uiLoginState.value = UiState.Error(it.message ?: "")
            }.collect {
                _uiLoginState.value = UiState.Success(it)
            }
        }
    }

}