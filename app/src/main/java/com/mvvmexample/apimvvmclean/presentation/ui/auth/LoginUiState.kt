package com.mvvmexample.apimvvmclean.presentation.ui.auth

import com.mvvmexample.apimvvmclean.domain.model.LoginResponse

data class LoginUiState(
    val isLoading: Boolean = false,
    val user: LoginResponse? = null,
    val error: String? = null
)