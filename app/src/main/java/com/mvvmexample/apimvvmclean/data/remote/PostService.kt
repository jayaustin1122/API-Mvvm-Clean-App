package com.mvvmexample.apimvvmclean.data.remote

import com.mvvmexample.apimvvmclean.data.modelDto.ListPostsDto
import com.mvvmexample.apimvvmclean.data.modelDto.PostDto
import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface PostService {
    @GET("posts")
    suspend fun getAllPosts(): PostDto

    @PUT("posts/add")
    suspend fun addPost(@Body request: ListPosts): ListPostsDto
}