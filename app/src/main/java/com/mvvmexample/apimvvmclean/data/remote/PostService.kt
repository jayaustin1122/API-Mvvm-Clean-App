package com.mvvmexample.apimvvmclean.data.remote

import com.mvvmexample.apimvvmclean.data.modelDto.PostDto
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    suspend fun getAllPosts(): PostDto
}