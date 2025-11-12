package com.project.core.di

import android.content.Context
import com.project.core.network.NetworkFactory
import com.project.core.network.UserService
import com.project.core.repo.UserRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Koin module untuk layer :core (Retrofit-based).
 * - Expose Dispatchers.IO agar testable
 * - Expose GreetingRepository
 * - Expose OkHttpClient dan Retrofit instance untuk akses API
 */
val coreModule = module {
    // Dispatcher testable
    single { Dispatchers.IO }

    // Repository reusable
    single { UserRepository(io = get(), service = get()) }

    // OkHttpClient dengan Chucker saat debug
    single {
        val context: Context = get()
        NetworkFactory.createOkHttp(
            context = context, enableDebugInterceptor = true
        )
    }

    // Retrofit singleton — gunakan BASE_URL dari ApiConfig
    single<Retrofit> {
        NetworkFactory.createRetrofit(
            baseUrl = com.project.core.network.ApiConfig.BASE_URL, client = get()
        )
    }

    // Registrasi UserService agar bisa di-inject via Koin
    single<UserService> {
        get<Retrofit>().create(UserService::class.java)
    }
}
