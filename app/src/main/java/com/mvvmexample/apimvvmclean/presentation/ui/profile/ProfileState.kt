package com.mvvmexample.apimvvmclean.presentation.ui.profile

import com.mvvmexample.apimvvmclean.data.model.LoginResponseDto

data class ProfileState(
    val isLoading: Boolean = false,
    val userProfile: LoginResponseDto? = null,
    val error: String = ""
)