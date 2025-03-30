package com.mvvmexample.apimvvmclean.presentation.state

import com.mvvmexample.apimvvmclean.domain.model.User

data class UsersState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String = ""
)