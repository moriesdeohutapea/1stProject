// core/src/main/java/com/project/core/domain/repository/UserRepository.kt
package com.project.core.domain.repository

import com.project.core.domain.model.UserEntity

interface UserRepository {
    suspend fun getUsers(): List<UserEntity>
}
