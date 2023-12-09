package com.example.thrivein.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thrivein.data.local.model.Article
import com.example.thrivein.data.network.request.ArticleRequest
import com.example.thrivein.data.network.response.article.ArticlesResponse
import com.example.thrivein.data.network.response.banner.BannerResponse
import com.example.thrivein.data.network.response.service.ServiceCategoriesResponse
import com.example.thrivein.data.repository.article.ArticleRepository
import com.example.thrivein.data.repository.banner.BannerRepository
import com.example.thrivein.data.repository.service.ServiceCategoryRepository
import com.example.thrivein.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val serviceCategoryRepository: ServiceCategoryRepository,
    private val articleRepository: ArticleRepository,
    private val bannerRepository: BannerRepository
) : ViewModel() {

    //Service
    private val _uiListThriveInServiceCategoryState: MutableStateFlow<UiState<ServiceCategoriesResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiListThriveInServiceCategoryState: StateFlow<UiState<ServiceCategoriesResponse>>
        get() = _uiListThriveInServiceCategoryState

    //Article
//    private val _uiListArticleState: MutableStateFlow<UiState<List<Article>>> =
//        MutableStateFlow(UiState.Loading)
//
//    val uiListArticleState: StateFlow<UiState<List<Article>>>
//        get() = _uiListArticleState

    //Article
    private val _uiListArticleState: MutableStateFlow<UiState<ArticlesResponse>> =
        MutableStateFlow(UiState.Loading)

    val uiListArticleState: StateFlow<UiState<ArticlesResponse>>
        get() = _uiListArticleState

    //Banner
    private val _uiThriveInBannerState: MutableStateFlow<UiState<BannerResponse>> =
        MutableStateFlow(UiState.Loading)
    val uiThriveInBannerState: StateFlow<UiState<BannerResponse>>
        get() = _uiThriveInBannerState

    fun getAllServiceCategory() {
        viewModelScope.launch {
            serviceCategoryRepository.getAllServiceCategories()
                .catch {
                    _uiListThriveInServiceCategoryState.value = UiState.Error(it.message.toString())
                }
                .collect { serviceCategories ->
                    _uiListThriveInServiceCategoryState.value = UiState.Success(serviceCategories)
                }
        }
    }

//    fun getAllArticlesHome(articleRequest: ArticleRequest) {
        fun getAllArticlesHome(size: Int, page: Int) {
    viewModelScope.launch {
            articleRepository.getAllArticlesHome(size, page)
                .catch {
                    _uiListArticleState.value = UiState.Error(it.message.toString())
                }
                .collect { article ->
                    _uiListArticleState.value = UiState.Success(article)
                }
        }
    }

    fun getAllBannerSlider() {
        viewModelScope.launch {
            bannerRepository.getAllBannerSlider()
                .catch {
                    _uiThriveInBannerState.value = UiState.Error(it.message.toString())
                }
                .collect { banners ->
                    _uiThriveInBannerState.value = UiState.Success(banners)
                }
        }
    }
}