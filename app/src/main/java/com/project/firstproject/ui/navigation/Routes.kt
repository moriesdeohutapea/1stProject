package com.project.firstproject.ui.navigation

/**
 * Definisi seluruh route utama aplikasi.
 * Gunakan sealed class agar aman dan terstruktur.
 */
enum class Routes {
    USER_LIST, USER_DETAIL;

    val route: String
        get() = name.lowercase()

    val routeWithArgs: String
        get() = when (this) {
            USER_DETAIL -> "${route}/{userId}"
            else -> route
        }

    fun buildRoute(vararg args: String): String {
        return route + args.joinToString(prefix = "/", separator = "/")
    }
}