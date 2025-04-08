package com.mvvmexample.apimvvmclean.domain.usecase

import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.PostsRepository
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(
    private val repository: PostsRepository
) {
    operator fun invoke(): Flow<Response<List<ListPosts>>> {
        return repository.getAllPosts()
    }
}