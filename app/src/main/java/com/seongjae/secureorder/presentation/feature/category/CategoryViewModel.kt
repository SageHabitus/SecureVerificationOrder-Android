package com.seongjae.secureorder.presentation.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seongjae.secureorder.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryState())
    val state: StateFlow<CategoryState> = _state

    fun loadCategories() = viewModelScope.launch {
        runCatching {
            menuRepository.getMenuCategories()
        }.onSuccess {
            _state.value =
                CategoryState(viewState = CategoryViewState.CategoryLoaded(it.first()))
        }.onFailure {
            _state.value = CategoryState(viewState = CategoryViewState.Error(it.message))
        }
    }
}
