package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.data.remote.CommentService
import com.mvvmexample.apimvvmclean.domain.model.Comment
import com.mvvmexample.apimvvmclean.domain.repository.CommentByIdRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CommentByIdRepositoryImpl @Inject constructor(
    private val api: CommentService
) : CommentByIdRepository {

    override fun getCommentById(id: Int): Flow<Response<List<Comment>>> = flow {
        emit(Response.Loading())

        try {
            val response = api.getCommentById(id)
            val comments = response.comments?.map { it.toDomainModel() } ?: emptyList()
            emit(Response.Success(comments))
        } catch (e: HttpException) {
            emit(Response.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Response.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }
}