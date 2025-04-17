package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.common.util.Constants
import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.data.remote.dummyjson.ApiService
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.di.DummyJsonRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    @DummyJsonRetrofit private val api: ApiService
) : UserRepository {

    override fun getUsers(): Flow<Response<List<User>>> = flow {
        emit(Response.Loading())

        try {
            val response = api.getUsers()
            val users = response.users.map { it.toDomainModel() }
            emit(Response.Success(users))
        } catch (e: HttpException) {
            emit(Response.Error(message = Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Response.Error(message = Constants.IO_EXCEPTION))
        }
    }

    override fun getUserById(id: Int): Flow<Response<User>> = flow {
        emit(Response.Loading())

        try {
            val user = api.getUserById(id).toDomainModel()
            emit(Response.Success(user))
        } catch (e: HttpException) {
            emit(Response.Error(message = Constants.ERROR_OCCURRED))
        } catch (e: IOException) {
            emit(Response.Error(message = Constants.IO_EXCEPTION))
        }
    }
}