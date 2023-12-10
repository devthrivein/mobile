package com.example.thrivein.ui.screen.article.detail

import androidx.lifecycle.ViewModel
import com.example.thrivein.data.network.response.service.ServiceResponse
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DetailArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    private val _uiServiceResponseState: MutableStateFlow<UiState<ServiceResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiServiceResponseState: StateFlow<UiState<ServiceResponse>>
        get() = _uiServiceResponseState

}