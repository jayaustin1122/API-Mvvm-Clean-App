package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.common.util.Constants
import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.data.remote.dummyjson.CommentService
import com.mvvmexample.apimvvmclean.domain.model.Comment
import com.mvvmexample.apimvvmclean.domain.repository.CommentByIdRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.di.DummyJsonRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CommentByIdRepositoryImpl @Inject constructor(
    @DummyJsonRetrofit private val api: CommentService
) : CommentByIdRepository {

    override fun getCommentById(id: Int): Flow<Response<List<Comment>>> = flow {
        emit(Response.Loading())

        try {
            val response = api.getCommentById(id)
            val comments = response.comments?.map { it.toDomainModel() } ?: emptyList()
            emit(Response.Success(comments))
        } catch (e: HttpException) {
            emit(Response.Error(message = Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Response.Error(message = Constants.IO_EXCEPTION))
        }
    }
}