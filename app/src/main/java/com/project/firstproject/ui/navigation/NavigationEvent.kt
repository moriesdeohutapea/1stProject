package com.project.firstproject.ui.navigation

import com.project.core.domain.model.UserEntity

// path: ui/navigation/NavigationEvent.kt

/**
 * Abstraksi event navigasi yang bisa dikirim dari ViewModel ke UI.
 * Berguna kalau ingin memisahkan NavController dari ViewModel.
 */
sealed class NavigationEvent {
    data class NavigateTo(val route: String) : NavigationEvent()
    data object NavigateUp : NavigationEvent()
    data class NavigateToDetail(val userEntity: UserEntity) : NavigationEvent()
}
