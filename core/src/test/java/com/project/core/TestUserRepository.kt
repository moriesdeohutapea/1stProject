package com.project.core

import com.project.core.data.repository.UserRepositoryImpl
import com.project.core.domain.model.UserEntity
import com.project.core.network.UserApi
import com.project.core.network.model.Address
import com.project.core.network.model.Company
import com.project.core.network.model.Geo
import com.project.core.network.model.UserResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class TestUserRepository {

    private lateinit var repository: UserRepositoryImpl
    private lateinit var api: UserApi

    @Before
    fun setup() {
        api = mock()
        repository = UserRepositoryImpl(api = api)
    }

    @Test
    fun `getUsers returns mapped UserEntity list`() = runTest {
        val mockResponse = listOf(
            UserResponse(
                id = 1, name = "John Doe", username = "johnd", email = "john@example.com", address = Address(
                    street = "Kulas Light",
                    suite = "Apt. 556",
                    city = "Gwenborough",
                    zipcode = "92998-3874",
                    geo = Geo(lat = "-37.3159", lng = "81.1496")
                ), phone = "1234567890", website = "example.com", company = Company(
                    name = "Romaguera-Crona",
                    catchPhrase = "Multi-layered client-server neural-net",
                    bs = "harness real-time e-markets"
                )
            )
        )
        whenever(api.getUsers()).thenReturn(mockResponse)

        val result: List<UserEntity> = repository.getUsers()

        assertEquals(1, result.size)
        assertEquals("John Doe", result.first().name)
        assertEquals("john@example.com", result.first().email)
    }
}