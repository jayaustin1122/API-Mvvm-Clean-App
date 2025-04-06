package com.mvvmexample.apimvvmclean.presentation.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmexample.apimvvmclean.domain.usecase.LoginUseCase
import com.mvvmexample.apimvvmclean.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase(username, password).collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true, error = null, user = null)
                        }
                    }

                    is Resource.Success -> {
                        _uiState.update {
                            it.copy(isLoading = false, user = result.data, error = null)
                        }
                    }

                    is Resource.Error -> {
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
}
