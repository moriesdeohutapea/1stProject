package com.project.firstproject.ui.screen.home.event

import com.project.core.domain.model.UserEntity

sealed interface MainEvent {
    data object Refresh : MainEvent
    data object Retry : MainEvent
    data class NavigateToDetail(val userEntity: UserEntity) : MainEvent
}