package com.seongjae.secureorder.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.seongjae.secureorder.presentation.feature.menu.MenuScreen
import com.seongjae.secureorder.presentation.navigation.MainNavigationRoute.MENU_GRAPH
import com.seongjae.secureorder.presentation.navigation.MenuNavRoute.CATEGORY
import com.seongjae.secureorder.presentation.navigation.MenuNavRoute.MENU

fun NavGraphBuilder.menuNavGraph(navController: NavHostController) {
    navigation(startDestination = MENU, route = MENU_GRAPH) {
        composable(MENU) { backStackEntry ->
            val category = backStackEntry.arguments?.getString(CATEGORY) ?: ""
            MenuScreen(category = category)
        }
    }
}

object MenuNavRoute {
    const val MENU = "menu/{category}"
    const val CATEGORY = "category"
}