package com.mvvmexample.apimvvmclean.di


import com.mvvmexample.apimvvmclean.data.repository.AuthRepositoryImpl
import com.mvvmexample.apimvvmclean.data.repository.CommentByIdRepositoryImpl
import com.mvvmexample.apimvvmclean.data.repository.GetAllPostsRepositoryImpl
import com.mvvmexample.apimvvmclean.data.repository.LoginRepositoryImpl
import com.mvvmexample.apimvvmclean.data.repository.UserRepositoryImpl
import com.mvvmexample.apimvvmclean.domain.repository.reqres.AuthRepository
import com.mvvmexample.apimvvmclean.domain.repository.CommentByIdRepository
import com.mvvmexample.apimvvmclean.domain.repository.LoginRepository
import com.mvvmexample.apimvvmclean.domain.repository.PostsRepository
import com.mvvmexample.apimvvmclean.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindLoginUserRepository(
        loginUserRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    @Singleton
    abstract fun bindGetAllPosts(
        getAllPostsRepositoryImpl: GetAllPostsRepositoryImpl
    ): PostsRepository

    @Binds
    @Singleton
    abstract fun bindGetAllCommentById(
        getCommentByIdRepositoryImpl: CommentByIdRepositoryImpl
    ): CommentByIdRepository

    @Binds
    @Singleton
    abstract fun bindRegisterUser(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository
}