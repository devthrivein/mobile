package com.example.thrivein

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.local.model.StoreModel
import com.example.thrivein.data.local.model.UserModel
import com.example.thrivein.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        viewModelScope.launch {
            _isLoading.value = true
            delay(1000)
            getUser()
            _isLoading.value = false
        }
    }

    fun getUser(): LiveData<UserModel> = authRepository.getUser().asLiveData()
    fun getStore(): LiveData<StoreModel> = authRepository.getStore().asLiveData()

    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
        }
    }

}