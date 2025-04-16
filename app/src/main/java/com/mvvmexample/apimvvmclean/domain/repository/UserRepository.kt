package com.mvvmexample.apimvvmclean.domain.repository

import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.common.util.Response
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUsers(): Flow<Response<List<User>>>

    fun getUserById(id: Int): Flow<Response<User>>

}

