package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.common.util.Constants
import com.mvvmexample.apimvvmclean.data.local.AuthPreferences
import com.mvvmexample.apimvvmclean.data.mapper.toDomainModel
import com.mvvmexample.apimvvmclean.data.modelDto.LoginRequestDto
import com.mvvmexample.apimvvmclean.data.remote.dummyjson.LoginService
import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.domain.repository.LoginRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.di.DummyJsonRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    @DummyJsonRetrofit private val api: LoginService,
    private val authPreferences: AuthPreferences
) : LoginRepository {

    override fun loginUser(username: String, password: String): Flow<Response<LoginResponse>> =
        flow {
            emit(Response.Loading())

            try {
                val request = LoginRequestDto(username = username, password = password)
                val response = api.loginUser(request)
                val user = response.toDomainModel()
                emit(Response.Success(user))
                authPreferences.saveAuthToken(response)
            } catch (e: HttpException) {
                emit(Response.Error(message = Constants.ERROR_OCCURRED))
            } catch (e: IOException) {
                emit(Response.Error(message = Constants.IO_EXCEPTION))
            }
        }
    override fun isAuthenticated(): Boolean {
        return authPreferences.isAuthenticated()
    }

    override fun logout() {
        authPreferences.clearAuthToken()
    }
}