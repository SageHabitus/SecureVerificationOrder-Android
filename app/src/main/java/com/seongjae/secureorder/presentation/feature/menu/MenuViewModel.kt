package com.seongjae.secureorder.presentation.feature.menu

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
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MenuState())
    val state: StateFlow<MenuState> = _state

    fun loadMenus(category: String) = viewModelScope.launch {
        _state.value = MenuState(viewState = MenuViewState.Loading)
        runCatching {
            menuRepository.getMenusByCategory(category)
        }.onSuccess {
            _state.value = MenuState(viewState = MenuViewState.MenusLoaded(it.first()))
        }.onFailure {
            _state.value = MenuState(viewState = MenuViewState.Error(it.message))
        }
    }
}
