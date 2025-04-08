package com.mvvmexample.apimvvmclean.data.model

data class PostDto (
    val posts: List<ListPostsDto>
)
data class ListPostsDto (
    val id: Long,
    val title: String,
    val body: String,
    val tags: List<String>,
    val reactions: ReactionsDto,
    val views: Long,
    val userID: Long
)

data class ReactionsDto (
    val likes: Long,
    val dislikes: Long
)
