package com.mvvmexample.apimvvmclean.data.remote


import com.mvvmexample.apimvvmclean.data.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>

    @GET("users/{id}")
    suspend fun getUserById(id: Int): UserDto
}