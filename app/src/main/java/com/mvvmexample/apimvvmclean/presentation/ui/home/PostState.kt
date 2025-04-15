package com.mvvmexample.apimvvmclean.presentation.ui.home

import com.mvvmexample.apimvvmclean.domain.model.Comment
import com.mvvmexample.apimvvmclean.domain.model.ListPosts

data class PostState(
    val isLoading: Boolean = false,
    val posts: List<ListPosts> = emptyList(),
    val error: String = "",
    val comment: List<Comment>? = emptyList(),
    val selectedPostId: Long? = null,
    val isLoadingComments: Boolean = false
)