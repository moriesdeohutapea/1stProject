package com.project.core.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Factory untuk membuat OkHttpClient yang aman dipakai ulang.
 * - Timeout konservatif
 * - Chucker hanya aktif saat enableDebugInterceptor = true
 */
object NetworkFactory {

    fun createOkHttp(
        context: Context,
        enableDebugInterceptor: Boolean = false,
        connectTimeoutSec: Long = 15,
        readTimeoutSec: Long = 30,
        writeTimeoutSec: Long = 30
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(connectTimeoutSec, TimeUnit.SECONDS)
            .readTimeout(readTimeoutSec, TimeUnit.SECONDS)
            .writeTimeout(writeTimeoutSec, TimeUnit.SECONDS)
            .apply {
                if (enableDebugInterceptor) {
                    addInterceptor(ChuckerInterceptor(context))
                }
            }
            .build()
    }

    fun createRetrofit(
        baseUrl: String,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inline fun <reified T> createService(retrofit: Retrofit): T =
        retrofit.create(T::class.java)
}
