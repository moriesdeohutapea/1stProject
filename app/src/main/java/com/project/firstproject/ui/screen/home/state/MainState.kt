package com.project.firstproject.ui.screen.home.state

import com.project.core.domain.model.UserEntity

data class MainState(
    val isLoading: Boolean = false,
    val data: List<UserEntity> = emptyList(),
    val error: String? = null,
)