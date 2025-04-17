package com.mvvmexample.apimvvmclean.domain.usecase

import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.domain.repository.LoginRepository
import com.mvvmexample.apimvvmclean.common.util.Response
import com.mvvmexample.apimvvmclean.data.modelDto.RegisterRequestDto
import com.mvvmexample.apimvvmclean.data.modelDto.RegisterResponseDto
import com.mvvmexample.apimvvmclean.domain.repository.reqres.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(username: String, password: String): Flow<Response<RegisterResponseDto>> {
        return repository.signUpUser(username, password)
    }

}