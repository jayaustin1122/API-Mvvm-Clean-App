package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.data.remote.ApiService
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UserRepository {

    override fun getUsers(): Flow<Response<List<User>>> = flow {
        emit(Response.Loading())

        try {
            val response = api.getUsers()
            val users = response.users.map { it.toDomainModel() }
            emit(Response.Success(users))
        } catch (e: HttpException) {
            emit(Response.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Response.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }

    override fun getUserById(id: Int): Flow<Response<User>> = flow {
        emit(Response.Loading())

        try {
            val user = api.getUserById(id).toDomainModel()
            emit(Response.Success(user))
        } catch (e: HttpException) {
            emit(Response.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Response.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }
}