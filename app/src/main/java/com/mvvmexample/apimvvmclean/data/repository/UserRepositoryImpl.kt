package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.data.local.AuthPreferences
import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.data.model.LoginRequestDto
import com.mvvmexample.apimvvmclean.data.remote.ApiService
import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: ApiService
) : UserRepository {

    override fun getUsers(): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())

        try {
            val response = api.getUsers()
            val users = response.users.map { it.toDomainModel() }
            emit(Resource.Success(users))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }

    override fun getUserById(id: Int): Flow<Resource<User>> = flow {
        emit(Resource.Loading())

        try {
            val user = api.getUserById(id).toDomainModel()
            emit(Resource.Success(user))
        } catch (e: HttpException) {
            emit(Resource.Error(message = "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
        }
    }
}