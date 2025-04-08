package com.mvvmexample.apimvvmclean.presentation.ui.home

import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.domain.model.User

data class PostState(
    val isLoading: Boolean = false,
    val posts: List<ListPosts> = emptyList(),
    val error: String = ""
)