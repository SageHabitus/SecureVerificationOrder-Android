package com.seongjae.secureorder.presentation.feature.menu

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seongjae.secureorder.data.model.MenuDataModel
import com.seongjae.secureorder.presentation.common.ErrorScreen
import com.seongjae.secureorder.presentation.common.LoadingScreen

@Composable
fun MenuScreen(
    category: String,
    viewModel: MenuViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(category) {
        viewModel.loadMenus(category)
    }

    when (val viewState = state.viewState) {
        is MenuViewState.Loading -> LoadingScreen()
        is MenuViewState.MenusLoaded -> MenuList(
            menus = viewState.menus
        )

        is MenuViewState.Error -> ErrorScreen(
            message = viewState.message ?: "Failed to load menus"
        )

        else -> {}
    }
}

@Composable
fun MenuList(menus: List<MenuDataModel>) {
    LazyColumn {
        items(menus) { menu ->
            Text(menu.name)
        }
    }
}

