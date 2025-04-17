package com.mvvmexample.apimvvmclean.data.remote.dummyjson

import com.mvvmexample.apimvvmclean.data.mapper.CommentResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {
    @GET("comments/post/{id}")
    suspend fun getCommentById(@Path("id") id: Int): CommentResponseDto
}