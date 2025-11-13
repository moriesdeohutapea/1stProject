package com.project.firstproject.ui.screen.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.core.domain.usecase.GetUsersUseCase
import com.project.firstproject.ui.navigation.NavigationEvent
import com.project.firstproject.ui.navigation.Screen
import com.project.firstproject.ui.screen.home.event.MainEvent
import com.project.firstproject.ui.screen.home.state.MainState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MainViewModel(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()
    val navigationEvent: SharedFlow<NavigationEvent> = _navigationEvent.asSharedFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            MainEvent.Refresh,
            MainEvent.Retry,
                -> fetchUsers(force = true)

            is MainEvent.NavigateToDetail -> {
                viewModelScope.launch {
                    _navigationEvent.emit(
                        NavigationEvent.NavigateTo(
                            Screen.UserDetail.createRoute(event.userEntity.id.toString())
                        )
                    )
                }
            }
        }
    }

    private fun fetchUsers(force: Boolean = false) {
        if (_state.value.isLoading && !force) return
        _state.value = _state.value.copy(isLoading = true, error = null)

        viewModelScope.launch {
            val maxRetry = 3
            var attempt = 0
            var success = false

            while (attempt < maxRetry && !success) {
                try {
                    val users = getUsersUseCase()
                    _state.value = MainState(isLoading = false, data = users)
                    success = true
                } catch (e: CancellationException) {
                    throw e
                } catch (e: SocketTimeoutException) {
                    attempt++
                    if (attempt >= maxRetry) handleError(e)
                    else delay(500L * attempt)
                } catch (e: UnknownHostException) {
                    handleError(e)
                    return@launch
                } catch (e: IOException) {
                    attempt++
                    if (attempt >= maxRetry) handleError(e)
                    else delay(500L * attempt)
                } catch (e: HttpException) {
                    if (e.code() >= 500 && attempt < maxRetry) {
                        attempt++
                        delay(500L * attempt)
                    } else {
                        handleError(e)
                        return@launch
                    }
                } catch (e: Exception) {
                    handleError(e)
                    return@launch
                }
            }
        }
    }

    private fun handleError(e: Throwable) {
        _state.value = MainState(isLoading = false, error = mapErrorMessage(e))
    }

    private fun mapErrorMessage(t: Throwable): String {
        return when (t) {
            is UnknownHostException -> "Tidak ada koneksi internet"
            is SocketTimeoutException -> "Koneksi timeout, coba lagi"
            is IOException -> "Gangguan jaringan, periksa koneksi"
            is HttpException -> {
                val code = t.code()
                if (code >= 500) "Server bermasalah ($code)"
                else "Permintaan gagal ($code)"
            }

            else -> t.message ?: "Terjadi kesalahan tak terduga"
        }
    }
}