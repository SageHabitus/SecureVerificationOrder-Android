package com.seongjae.secureorder.presentation.feature.menu

import com.seongjae.secureorder.data.model.MenuDataModel

data class MenuState(
    val viewState: MenuViewState = MenuViewState.Idle,
)

sealed interface MenuViewState {
    data object Idle : MenuViewState
    data object Loading : MenuViewState
    data class MenusLoaded(val menus: List<MenuDataModel>) : MenuViewState
    data class Error(val message: String?) : MenuViewState
}
