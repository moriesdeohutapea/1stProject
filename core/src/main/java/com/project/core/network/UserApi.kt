package com.project.core.network

import com.project.core.network.model.UserResponse
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>
}
