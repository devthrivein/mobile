package com.example.thrivein.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.local.model.Article
import com.example.thrivein.data.local.model.ThriveInServiceCategory
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.data.repository.service.ServiceCategoryRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val serviceCategoryRepository: ServiceCategoryRepository,
    private val articleRepository: ArticleRepository,
) : ViewModel() {

    private val _uiListThriveInServiceCategoryState: MutableStateFlow<UiState<List<ThriveInServiceCategory>>> =
        MutableStateFlow(UiState.Loading)

    val uiListThriveInServiceCategoryState: StateFlow<UiState<List<ThriveInServiceCategory>>>
        get() = _uiListThriveInServiceCategoryState

    private val _uiListArticleState: MutableStateFlow<UiState<List<Article>>> =
        MutableStateFlow(UiState.Loading)

    val uiListArticleState: StateFlow<UiState<List<Article>>>
        get() = _uiListArticleState


    fun getAllServiceCategory() {
        viewModelScope.launch {
            serviceCategoryRepository.getAllService()
                .catch {
                    _uiListThriveInServiceCategoryState.value = UiState.Error(it.message.toString())
                }
                .collect { serviceCategory ->
                    _uiListThriveInServiceCategoryState.value = UiState.Success(serviceCategory)
                }
        }
    }

    fun getAllArticle() {
        viewModelScope.launch {
            articleRepository.getAllArticle()
                .catch {
                    _uiListArticleState.value = UiState.Error(it.message.toString())
                }
                .collect { article ->
                    _uiListArticleState.value = UiState.Success(article)
                }
        }
    }
}