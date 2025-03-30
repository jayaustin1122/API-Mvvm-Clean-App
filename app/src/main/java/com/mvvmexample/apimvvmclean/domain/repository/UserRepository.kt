package com.mvvmexample.apimvvmclean.domain.repository


import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUsers(): Flow<Resource<List<User>>>
    fun getUserById(id: Int): Flow<Resource<User>>
}
