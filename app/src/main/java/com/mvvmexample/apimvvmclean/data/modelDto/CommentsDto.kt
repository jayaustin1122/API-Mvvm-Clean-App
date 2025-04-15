package com.mvvmexample.apimvvmclean.data.modelDto

data class CommentDto (
    val id: Long,
    val body: String,
    val postId: Long,
    val likes: Long,
    val user: UserCommentDto?
)

data class UserCommentDto(
    val id: Long,
    val username: String,
    val fullName: String
)

data class ListCommentDto(
    val listComment : List<CommentDto>,
)

