package com.mvvmexample.apimvvmclean.data.remote

import com.mvvmexample.apimvvmclean.data.mapper.CommentResponseDto
import com.mvvmexample.apimvvmclean.data.modelDto.CommentDto
import com.mvvmexample.apimvvmclean.data.modelDto.ListCommentDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {
    @GET("comments/post/{id}")
    suspend fun getCommentById(@Path("id") id: Int): CommentResponseDto
}