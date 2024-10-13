package com.seongjae.secureorder.presentation.feature.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.seongjae.secureorder.presentation.common.ErrorScreen
import com.seongjae.secureorder.presentation.common.LoadingScreen

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onCategorySelected: (String) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val viewState = state.viewState) {
        is CategoryViewState.Loading -> LoadingScreen()
        is CategoryViewState.CategoryLoaded -> CategoryList(
            categories = viewState.categories,
            onCategorySelected = onCategorySelected
        )

        is CategoryViewState.Error -> ErrorScreen(
            message = viewState.message ?: "Failed to load categories"
        )

        else -> {}
    }
}

@Composable
fun CategoryList(categories: List<String>, onCategorySelected: (String) -> Unit) {
    LazyColumn {
        items(categories) { category ->
            Text(category, Modifier.clickable { onCategorySelected(category) })
        }
    }
}
