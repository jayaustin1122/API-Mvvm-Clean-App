package com.mvvmexample.apimvvmclean.data.remote.reqres

import com.mvvmexample.apimvvmclean.data.modelDto.RegisterRequestDto
import com.mvvmexample.apimvvmclean.data.modelDto.RegisterResponseDto
import retrofit2.http.Body
import retrofit2.http.POST

interface ReqResAuthService {
    @POST("register")
    suspend fun register(@Body request: RegisterRequestDto): RegisterResponseDto
}
