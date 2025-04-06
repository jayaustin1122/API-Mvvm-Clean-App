package com.mvvmexample.apimvvmclean.domain.model

data class LoginRequest(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)

data class LoginResponse(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val token: String
)
