package com.mvvmexample.apimvvmclean.data.mapper

import com.mvvmexample.apimvvmclean.data.modelDto.CommentDto
import com.mvvmexample.apimvvmclean.data.modelDto.ListCommentDto
import com.mvvmexample.apimvvmclean.data.modelDto.ListPostsDto
import com.mvvmexample.apimvvmclean.data.modelDto.PostDto
import com.mvvmexample.apimvvmclean.data.modelDto.ReactionsDto
import com.mvvmexample.apimvvmclean.data.modelDto.UserCommentDto
import com.mvvmexample.apimvvmclean.domain.model.Comment
import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.domain.model.Post
import com.mvvmexample.apimvvmclean.domain.model.Reactions
import com.mvvmexample.apimvvmclean.domain.model.UserComment

fun CommentDto.toDomainModel(): Comment {
    return Comment(
        id = id,
        body = body ?: "",
        postId = postId,
        likes = likes,
        user = user?.toDomainModel(),
    )
}

fun UserCommentDto.toDomainModel(): UserComment {
    return UserComment(
        id = id,
        username = username,
        fullName = fullName
    )
}

fun ListCommentDto.toDomainModel(): List<Comment> {
    return listComment.map { it.toDomainModel() }
}
data class CommentResponseDto(
    val comments: List<CommentDto>? = null,
    val total: Int? = null,
    val skip: Int? = null,
    val limit: Int? = null
)
