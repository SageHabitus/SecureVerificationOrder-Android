package com.seongjae.secureorder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.seongjae.secureorder.presentation.main.AuthState
import com.seongjae.secureorder.presentation.main.MainViewModel

@Composable
fun MainNavigation(
    mainViewModel: MainViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController()
) {
    val state by mainViewModel.state.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = if (state is AuthState.Authenticated) MainNavigationRoute.MENU_GRAPH else MainNavigationRoute.AUTH_GRAPH
    ) {
        authNavGraph(navController = navController)
        menuNavGraph(navController = navController)
    }
}

object MainNavigationRoute {
    const val AUTH_GRAPH = "auth_graph"
    const val MENU_GRAPH = "menu_graph"
}