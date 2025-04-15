package com.mvvmexample.apimvvmclean.domain.model

data class Comment (
    val id: Long,
    val body: String?,
    val postId: Long,
    val likes: Long,
    val user: UserComment?
)

data class UserComment (
    val id: Long,
    val username: String,
    val fullName: String
)


