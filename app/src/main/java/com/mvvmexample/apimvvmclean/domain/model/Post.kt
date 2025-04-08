package com.mvvmexample.apimvvmclean.domain.model

data class Post (
    val posts: List<ListPosts>
)
data class ListPosts (
    val id: Long,
    val title: String,
    val body: String,
    val tags: List<String>,
    val reactions: Reactions,
    val views: Long,
    val userID: Long
)

data class Reactions (
    val likes: Long,
    val dislikes: Long
)