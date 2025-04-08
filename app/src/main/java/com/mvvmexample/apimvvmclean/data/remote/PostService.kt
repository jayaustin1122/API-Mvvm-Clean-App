package com.mvvmexample.apimvvmclean.data.remote

import com.mvvmexample.apimvvmclean.data.model.PostDto
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    suspend fun getAllPosts(): PostDto
}