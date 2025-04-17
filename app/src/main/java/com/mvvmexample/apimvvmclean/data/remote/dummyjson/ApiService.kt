package com.mvvmexample.apimvvmclean.data.remote.dummyjson

import com.mvvmexample.apimvvmclean.data.modelDto.UserDto
import com.mvvmexample.apimvvmclean.data.modelDto.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto

}