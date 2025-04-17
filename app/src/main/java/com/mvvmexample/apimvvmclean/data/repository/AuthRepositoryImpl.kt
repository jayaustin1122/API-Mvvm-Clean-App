package com.mvvmexample.apimvvmclean.data.repository

import com.mvvmexample.apimvvmclean.common.util.Constants
import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.data.modelDto.RegisterRequestDto
import com.mvvmexample.apimvvmclean.data.modelDto.RegisterResponseDto
import com.mvvmexample.apimvvmclean.data.remote.reqres.ReqResAuthService
import com.mvvmexample.apimvvmclean.di.ReqResRetrofit
import com.mvvmexample.apimvvmclean.domain.repository.reqres.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    @ReqResRetrofit private val api: ReqResAuthService
) : AuthRepository {

    override fun signUpUser(email: String, password: String): Flow<Response<RegisterResponseDto>> =
        flow {
            emit(Response.Loading())
            try {
                val request = RegisterRequestDto(email, password)
                val response = api.register(request)
                emit(Response.Success(response))
            } catch (e: HttpException) {
                emit(Response.Error(message = Constants.ERROR_OCCURRED))
            } catch (e: IOException) {
                emit(Response.Error(message = Constants.IO_EXCEPTION))
            }
        }
}