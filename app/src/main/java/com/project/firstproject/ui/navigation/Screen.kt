package com.project.firstproject.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {

    data object UserList : Screen(Routes.USER_LIST)

    data object UserDetail : Screen("${Routes.USER_DETAIL}/{userId}") {
        const val routeWithArgs = "${Routes.USER_DETAIL}/{userId}"

        fun createRoute(userId: String): String = "${Routes.USER_DETAIL}/$userId"

        val navArguments = listOf(
            navArgument("userId") { type = NavType.StringType })
    }
}
