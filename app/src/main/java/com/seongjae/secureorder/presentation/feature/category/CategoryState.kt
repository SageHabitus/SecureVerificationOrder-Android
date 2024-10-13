package com.seongjae.secureorder.presentation.feature.category

data class CategoryState(
    val viewState: CategoryViewState = CategoryViewState.Idle,
)

sealed interface CategoryViewState {
    data object Idle : CategoryViewState
    data object Loading : CategoryViewState
    data class CategoryLoaded(val categories: List<String>) : CategoryViewState
    data class Error(val message: String?) : CategoryViewState
}