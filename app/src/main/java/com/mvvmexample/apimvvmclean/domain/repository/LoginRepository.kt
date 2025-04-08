package com.mvvmexample.apimvvmclean.domain.repository

import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.util.Response
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun loginUser(username: String, password: String): Flow<Response<LoginResponse>>

    fun isAuthenticated(): Boolean

    fun logout()
}

