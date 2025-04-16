package com.mvvmexample.apimvvmclean.domain.usecase

import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.domain.repository.PostsRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddPostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    operator fun invoke(post: ListPosts): Flow<Response<ListPosts>> {
        return repository.addPost(post)
    }
}