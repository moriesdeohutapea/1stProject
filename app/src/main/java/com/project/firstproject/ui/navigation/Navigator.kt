// path: ui/navigation/Navigator.kt
package com.project.firstproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

/**
 * Abstraksi sederhana di atas NavHostController supaya ViewModel
 * hanya mengirim NavigationEvent, bukan langsung memegang NavController.
 */
class Navigator(
    private val navController: NavHostController,
) {

    fun handleEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.NavigateTo -> navController.navigate(event.route)
            NavigationEvent.NavigateUp -> navController.navigateUp()
        }
    }

    fun navigateTo(route: String) {
        navController.navigate(route)
    }

    fun navigateUp() {
        navController.navigateUp()
    }
}

/**
 * Helper untuk membuat Navigator yang di-recreate hanya ketika
 * instance NavHostController berubah.
 */
@Composable
fun rememberNavigator(
    navController: NavHostController,
): Navigator = remember(navController) {
    Navigator(navController)
}
