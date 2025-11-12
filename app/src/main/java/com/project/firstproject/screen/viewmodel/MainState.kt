package com.project.firstproject.screen.viewmodel

import com.project.core.network.UserResponse

data class MainState(
    val isLoading: Boolean = false,
    val data: List<UserResponse> = emptyList(),
    val error: String? = null,
)
