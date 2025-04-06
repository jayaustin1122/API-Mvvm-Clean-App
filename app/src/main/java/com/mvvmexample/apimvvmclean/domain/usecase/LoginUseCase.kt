package com.mvvmexample.apimvvmclean.domain.usecase

import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.domain.repository.LoginRepository
import com.mvvmexample.apimvvmclean.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    operator fun invoke(username: String, password: String): Flow<Resource<LoginResponse>> {
        return repository.loginUser(username, password)
    }

    fun isAuthenticated(): Boolean {
        return repository.isAuthenticated()
    }

    fun logout() {
        repository.logout()
    }
}