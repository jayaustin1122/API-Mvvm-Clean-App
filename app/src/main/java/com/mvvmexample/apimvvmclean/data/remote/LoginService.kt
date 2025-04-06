package com.mvvmexample.apimvvmclean.data.remote

import com.mvvmexample.apimvvmclean.data.model.LoginRequestDto
import com.mvvmexample.apimvvmclean.data.model.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("user/login")
    suspend fun loginUser(@Body request: LoginRequestDto): LoginResponseDto
}