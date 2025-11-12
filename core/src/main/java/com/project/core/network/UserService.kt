// 📂 Path: core/src/main/java/com/project/core/network/UserService.kt
package com.project.core.network

import retrofit2.http.GET

data class Geo(
    val lat: String,
    val lng: String,
)

data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo,
)

data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String,
)

data class UserResponse(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company,
)

interface UserService {
    @GET(PATH_USERS)
    suspend fun getUsers(): List<UserResponse>

    companion object {
        const val PATH_USERS = "users"
    }
}