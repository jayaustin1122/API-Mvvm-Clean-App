package com.mvvmexample.apimvvmclean.domain.repository

import com.mvvmexample.apimvvmclean.domain.model.LoginResponse
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun loginUser(username: String, password: String): Flow<Resource<LoginResponse>>

    fun isAuthenticated(): Boolean

    fun logout()
}

