package com.project.core.domain.model

data class UserEntity(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressEntity,
    val phone: String,
    val website: String,
    val company: CompanyEntity,
)

data class GeoEntity(
    val lat: String,
    val lng: String,
)

data class AddressEntity(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoEntity,
)

data class CompanyEntity(
    val name: String,
    val catchPhrase: String,
    val bs: String,
)
