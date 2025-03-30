package com.mvvmexample.apimvvmclean.presentation.state

import com.mvvmexample.apimvvmclean.domain.model.User

data class UserDetailState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String = ""
)