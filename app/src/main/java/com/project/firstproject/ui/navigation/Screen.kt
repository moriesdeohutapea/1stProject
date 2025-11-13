package com.project.firstproject.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {

    // List user (Home)
    data object UserList : Screen(Routes.USER_LIST.route)

    // Detail user
    data object UserDetail : Screen("${Routes.USER_DETAIL.route}/{userId}") {

        val routeWithArgs = "${Routes.USER_DETAIL.route}/{userId}"

        fun createRoute(userId: String): String = "${Routes.USER_DETAIL.route}/$userId"

        val navArguments = listOf(
            navArgument("userId") { type = NavType.StringType })
    }
}
