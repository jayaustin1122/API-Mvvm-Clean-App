package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.data.local.AuthPreferences
import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.data.model.LoginRequestDto
import com.mvvmexample.apimvvmclean.data.remote.ApiService
import com.mvvmexample.apimvvmclean.data.remote.LoginService
import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.LoginRepository
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService,
    private val authPreferences: AuthPreferences
) : LoginRepository {

    override fun loginUser(username: String, password: String): Flow<Resource<LoginResponse>> =
        flow {
            emit(Resource.Loading())

            try {
                val request = LoginRequestDto(username = username, password = password)
                val response = api.loginUser(request)
                val user = response.toDomainModel()
                emit(Resource.Success(user))
                authPreferences.saveAuthToken(response)
            } catch (e: HttpException) {
                emit(Resource.Error(message = "Invalid credentials or unexpected error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error(message = "Couldn't reach server. Check your internet connection"))
            }
        }
    override fun isAuthenticated(): Boolean {
        return authPreferences.isAuthenticated()
    }

    override fun logout() {
        authPreferences.clearAuthToken()
    }
}