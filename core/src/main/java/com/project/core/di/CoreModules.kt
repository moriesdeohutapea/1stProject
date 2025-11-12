// core/src/main/java/com/project/core/di/CoreModules.kt
package com.project.core.di

import com.project.core.domain.repository.UserRepository
import com.project.core.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

/**
 * Koin module untuk layer :core (Retrofit-based).
 * - Expose Dispatchers.IO agar testable
 * - Expose OkHttpClient dan Retrofit instance untuk akses API
 * - Bind UserRepository ke implementasinya
 */
val coreModule = module {

    single { GetUsersUseCase(repository = get<UserRepository>()) }
}
