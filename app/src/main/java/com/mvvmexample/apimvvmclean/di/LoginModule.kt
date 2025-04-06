package com.mvvmexample.apimvvmclean.di

import android.content.Context
import com.mvvmexample.apimvvmclean.data.local.AuthPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  LoginModule {

    @Provides
    @Singleton
    fun provideAuthPreferences(
        @ApplicationContext context: Context
    ): AuthPreferences {
        return AuthPreferences(context)
    }
}


