package com.mvvmexample.apimvvmclean.domain.repository.reqres

import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.data.modelDto.RegisterResponseDto
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun signUpUser(email: String, password: String): Flow<Response<RegisterResponseDto>>
}