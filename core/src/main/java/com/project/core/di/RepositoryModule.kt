// core/di/RepositoryModule.kt
package com.project.core.di

import com.project.core.data.repository.UserRepositoryImpl
import com.project.core.domain.repository.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
}
