package com.project.core.domain.usecase

import com.project.core.domain.repository.UserRepository

class GetUsersUseCase(
    private val repository: UserRepository,
) {
    suspend operator fun invoke() = repository.getUsers()
}