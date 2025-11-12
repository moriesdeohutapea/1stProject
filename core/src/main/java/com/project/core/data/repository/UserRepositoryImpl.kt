package com.project.core.data.repository

import com.project.core.domain.model.UserEntity
import com.project.core.domain.repository.UserRepository
import com.project.core.network.UserApi
import toEntity

class UserRepositoryImpl(
    private val api: UserApi,
) : UserRepository {
    override suspend fun getUsers(): List<UserEntity> {
        return api.getUsers().map { it.toEntity() }
    }
}