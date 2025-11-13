// 📂 Path: app/src/main/java/com/project/firstproject/di/AppModules.kt
package com.project.firstproject.di

import com.project.firstproject.ui.screen.home.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel(getUsersUseCase = get()) }
}