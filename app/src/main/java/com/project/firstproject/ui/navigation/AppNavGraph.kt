// path: ui/navigation/AppNavGraph.kt
package com.project.firstproject.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun AppNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
) {
    NavHost(
        navController = navController, startDestination = Screen.UserList.route
    ) {
        mainNavGraph(
            navController = navController,
            innerPadding = innerPadding,
        )
    }
}