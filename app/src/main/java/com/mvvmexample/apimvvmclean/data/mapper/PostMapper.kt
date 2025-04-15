package com.mvvmexample.apimvvmclean.data.mapper

import com.mvvmexample.apimvvmclean.data.modelDto.ListPostsDto
import com.mvvmexample.apimvvmclean.data.modelDto.PostDto
import com.mvvmexample.apimvvmclean.data.modelDto.ReactionsDto
import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.domain.model.Post
import com.mvvmexample.apimvvmclean.domain.model.Reactions

fun PostDto.toDomainModel(): Post {
    return Post(
        posts = posts.map { it.toDomainModel() }
    )
}

fun ListPostsDto.toDomainModel(): ListPosts {
    return ListPosts(
        id = id,
        title = title,
        body = body,
        tags = tags,
        reactions = reactions.toDomainModel(),
        views = views,
        userID = userID
    )
}

fun ReactionsDto.toDomainModel(): Reactions {
    return Reactions(
        likes = likes,
        dislikes = dislikes
    )
}