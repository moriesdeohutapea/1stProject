// 📂 Path: core/src/main/java/com/project/core/repo/UserRepository.kt
package com.project.core.repo

import com.project.core.network.UserService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Repository untuk mengambil data user dari API JSONPlaceholder.
 */
class UserRepository(
    private val io: CoroutineDispatcher,
    private val service: UserService,
) {
    suspend fun getUsers() = withContext(io) {
        service.getUsers()
    }
}
