package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.data.remote.PostService
import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.domain.repository.PostsRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllPostsRepositoryImpl @Inject constructor(
    private val api: PostService
) : PostsRepository {

    override fun getAllPosts(): Flow<Response<List<ListPosts>>> = flow {
        emit(Response.Loading())

        try {
            val response = api.getAllPosts()
            val posts = response.posts.map { it.toDomainModel() }
            emit(Response.Success(posts))
        } catch (e: HttpException) {
            emit(Response.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Response.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }

    override fun addPost(post: ListPosts): Flow<Response<ListPosts>> {
        return flow {
            emit(Response.Loading())

            try {
                val response = api.addPost(post)
                emit(Response.Success(response.toDomainModel()))
            } catch (e: HttpException) {
                emit(Response.Error(message = "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(Response.Error(message = "Couldn't reach server. Check your internet connection"))
            }
        }
    }
}