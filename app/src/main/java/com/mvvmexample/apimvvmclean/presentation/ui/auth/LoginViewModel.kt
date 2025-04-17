package com.mvvmexample.apimvvmclean.presentation.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.domain.usecase.LoginUseCase
import com.mvvmexample.apimvvmclean.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password).collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true, error = null, user = null)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(isLoading = false, user = result.data, error = null)
                        }
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(isLoading = false, error = result.message, user = null)
                        }
                    }
                }
            }
        }
    }

    fun signup(username: String, password: String) {
        viewModelScope.launch {
            signUpUseCase(username, password).collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true, error = null, user = null)
                        }
                    }

                    is Response.Success -> {
                        _uiState.update {
                            it.copy(isLoading = false, userRegister = result.data, error = null)
                        }
                        Log.d("TAG", "signup: ${result.data}")
                    }

                    is Response.Error -> {
                        _uiState.update {
                            it.copy(isLoading = false, error = result.message, user = null)
                        }
                    }

                }
            }
        }
    }

    fun isAuthenticated(): Boolean {
        return loginUseCase.isAuthenticated()
    }

    fun logout() {
        loginUseCase.logout()
    }

    fun setError(message: String) {
        _uiState.value = _uiState.value.copy(error = message)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

}
