package com.mvvmexample.apimvvmclean.domain.repository

import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.util.Response
import kotlinx.coroutines.flow.Flow

interface PostsRepository {
    fun getAllPosts(): Flow<Response<List<ListPosts>>>
}

