package com.project.firstproject.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.project.core.domain.model.UserEntity
import com.project.firstproject.ui.navigation.arg.UserDetailArg
import com.project.firstproject.ui.screen.detail.DetailScreen
import com.project.firstproject.ui.screen.home.HomeScreen
import com.project.firstproject.ui.screen.home.event.MainEvent
import com.project.firstproject.ui.screen.home.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

/**
 * Extension untuk memecah deklarasi NavGraph agar AppNavGraph tetap bersih.
 * Seluruh route utama aplikasi didefinisikan di sini.
 */
fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    composable(Screen.UserList.route) {
        val viewModel: MainViewModel = koinViewModel()
        val state by viewModel.state.collectAsState()
        val navigator = rememberNavigator(navController)

        LaunchedEffect(Unit) {
            viewModel.navigationEvent.collect { event ->
                navigator.handleEvent(event)
            }
        }

        LaunchedEffect(Unit) {
            viewModel.onEvent(MainEvent.Refresh)
        }

        HomeScreen(
            state = state, innerPadding = innerPadding, action = viewModel::onEvent, onClickItem = { user ->
                viewModel.onEvent(MainEvent.NavigateToDetail(user))
            }
        )
    }

    composable(
        route = Screen.UserDetail.route
    ) { backStackEntry ->
        val userEntity: UserEntity? =
            navController.previousBackStackEntry?.savedStateHandle?.get<UserEntity>(UserDetailArg.User.key)

        if (userEntity == null) {
            return@composable
        }

        DetailScreen(
            userEntity = userEntity,
            onNavigationClick = {
                navController.popBackStack()
            }
        )
    }
}
