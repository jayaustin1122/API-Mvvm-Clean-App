package com.mvvmexample.apimvvmclean.domain.usecase


import com.mvvmexample.apimvvmclean.domain.model.Comment
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.CommentByIdRepository
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.util.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommentByIdUseCase @Inject constructor(
    private val repository: CommentByIdRepository
) {
    operator fun invoke(id: Int): Flow<Response<List<Comment>>> {
        return repository.getCommentById(id)
    }
}
