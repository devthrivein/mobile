package com.example.thrivein.ui.screen.article.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListArticleViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    private val _uiListArticleState: MutableStateFlow<UiState<ArticlesResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiListArticleState: StateFlow<UiState<ArticlesResponse>>
        get() = _uiListArticleState


    fun getAllArticles(articleRequest: ArticleRequest) {
        viewModelScope.launch {
            articleRepository.getAllArticles(articleRequest)
                .catch {
                    _uiListArticleState.value = UiState.Error(it.message.toString())
                }
                .collect { article ->
                    _uiListArticleState.value = UiState.Success(article)
                }
        }
    }

}