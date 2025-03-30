package com.mvvmexample.apimvvmclean.domain.usecase


import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import com.mvvmexample.apimvvmclean.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(id: Int): Flow<Resource<User>> {
        return repository.getUserById(id)
    }
}
