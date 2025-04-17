package com.mvvmexample.apimvvmclean.data.remote.dummyjson

import com.mvvmexample.apimvvmclean.data.modelDto.LoginRequestDto
import com.mvvmexample.apimvvmclean.data.modelDto.LoginResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("user/login")
    suspend fun loginUser(@Body request: LoginRequestDto): LoginResponseDto
}