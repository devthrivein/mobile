package com.example.thrivein.ui.screen.article.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.response.article.DetailArticleResponse
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    private val _uiDetailArticleResponseState: MutableStateFlow<UiState<DetailArticleResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiDetailArticleResponseState: StateFlow<UiState<DetailArticleResponse>>
        get() = _uiDetailArticleResponseState


    fun getDetailArticle(articleId: String) {
        viewModelScope.launch {
            articleRepository.getDetailArticle(articleId).catch {
                _uiDetailArticleResponseState.value = UiState.Error(it.message.toString())
            }.collect { article ->
                _uiDetailArticleResponseState.value = UiState.Success(article)
            }
        }
    }

}