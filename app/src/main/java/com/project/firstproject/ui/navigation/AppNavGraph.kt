// path: ui/navigation/AppNavGraph.kt
package com.project.firstproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController, startDestination = Screen.UserList.route
    ) {
        composable(
            route = Screen.UserDetail.routeWithArgs, arguments = Screen.UserDetail.navArguments
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
        }
    }
}