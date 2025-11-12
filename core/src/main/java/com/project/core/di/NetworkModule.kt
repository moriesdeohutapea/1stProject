// core/di/NetworkModule.kt
package com.project.core.di

import android.content.Context
import com.project.core.network.NetworkFactory
import com.project.core.network.ApiConfig
import com.project.core.network.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single {
        val context: Context = get()
        NetworkFactory.createOkHttp(
            context = context,
            enableDebugInterceptor = com.project.core.BuildConfig.DEBUG
        )
    }

    single<Retrofit> {
        NetworkFactory.createRetrofit(
            baseUrl = ApiConfig.BASE_URL,
            client = get()
        )
    }

    single<UserApi> {
        get<Retrofit>().create(UserApi::class.java)
    }
}