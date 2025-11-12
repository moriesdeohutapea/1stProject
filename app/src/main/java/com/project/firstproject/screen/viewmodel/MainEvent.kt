package com.project.firstproject.screen.viewmodel

sealed interface MainEvent {
    data object Refresh : MainEvent
    data object Retry : MainEvent
}
