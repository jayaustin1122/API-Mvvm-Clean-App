package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.common.util.Constants
import com.mvvmexample.apimvvmclean.data.remote.dummyjson.PostService
import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.domain.repository.PostsRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.di.DummyJsonRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPostsRepositoryImpl @Inject constructor(
    @DummyJsonRetrofit private val api: PostService
) : PostsRepository {

    override fun getAllPosts(): Flow<Response<List<ListPosts>>> = flow {
        emit(Response.Loading())

        try {
            val response = api.getAllPosts()
            val posts = response.posts.map { it.toDomainModel() }
            emit(Response.Success(posts))
        } catch (e: HttpException) {
            emit(Response.Error(message = Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Response.Error(message = Constants.IO_EXCEPTION))
        }
    }

    override fun addPost(post: ListPosts): Flow<Response<ListPosts>> {
        return flow {
            emit(Response.Loading())

            try {
                val response = api.addPost(post)
                emit(Response.Success(response.toDomainModel()))
            } catch (e: HttpException) {
                emit(Response.Error(message = Constants.ERROR_OCCURRED))
            } catch (e: IOException) {
                emit(Response.Error(message = Constants.IO_EXCEPTION))
            }
        }
    }
}