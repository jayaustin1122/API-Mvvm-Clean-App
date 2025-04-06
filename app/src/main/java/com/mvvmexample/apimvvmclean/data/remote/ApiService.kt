package com.mvvmexample.apimvvmclean.data.remote

import com.mvvmexample.apimvvmclean.data.model.UserDto
import com.mvvmexample.apimvvmclean.data.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(): UsersResponse

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDto

}