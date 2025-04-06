package com.mvvmexample.apimvvmclean.data.model

data class LoginRequestDto(
    val username: String,
    val password: String,
    val expiresInMins: Int = 30
)

data class LoginResponseDto(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String
)
