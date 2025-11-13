package com.project.firstproject.ui.navigation

import androidx.navigation.NamedNavArgument

sealed class Screen(
    val route: String,
    val routeWithArgs: String = route,
    val arguments: List<NamedNavArgument> = emptyList(),
) {

    // List user (Home)
    data object UserList : Screen(Routes.USER_LIST.route)

    // Detail user (tanpa argumen di route)
    data object UserDetail : Screen(Routes.USER_DETAIL.route)
}