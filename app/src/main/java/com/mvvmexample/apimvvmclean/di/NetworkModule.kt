package com.mvvmexample.apimvvmclean.di

import com.mvvmexample.apimvvmclean.data.remote.dummyjson.ApiService
import com.mvvmexample.apimvvmclean.data.remote.dummyjson.CommentService
import com.mvvmexample.apimvvmclean.data.remote.dummyjson.LoginService
import com.mvvmexample.apimvvmclean.data.remote.dummyjson.PostService
import com.mvvmexample.apimvvmclean.data.remote.reqres.ReqResAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @DummyJsonRetrofit
    fun provideDummyJsonRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @ReqResRetrofit
    fun provideReqResRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @DummyJsonRetrofit
    fun provideApiService(@DummyJsonRetrofit retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Provides
    @Singleton
    @DummyJsonRetrofit
    fun provideLoginService(@DummyJsonRetrofit retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    @DummyJsonRetrofit
    fun providePostsService(@DummyJsonRetrofit retrofit: Retrofit): PostService {
        return retrofit.create(PostService::class.java)
    }

    @Provides
    @Singleton
    @DummyJsonRetrofit
    fun provideCommentByIdService(@DummyJsonRetrofit retrofit: Retrofit): CommentService {
        return retrofit.create(CommentService::class.java)
    }

    @Provides
    @Singleton
    @ReqResRetrofit
    fun provideAuthService(@ReqResRetrofit retrofit: Retrofit): ReqResAuthService {
        return retrofit.create(ReqResAuthService::class.java)
    }

}