package com.project.firstproject.ui.screen.home.event

sealed interface MainEvent {
    data object Refresh : MainEvent
    data object Retry : MainEvent
}