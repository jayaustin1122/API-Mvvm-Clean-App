package com.mvvmexample.apimvvmclean.data.local

import android.content.Context
import android.content.SharedPreferences
import com.mvvmexample.apimvvmclean.common.util.Constants.ACCESS_TOKEN
import com.mvvmexample.apimvvmclean.common.util.Constants.EMAIL
import com.mvvmexample.apimvvmclean.common.util.Constants.FIRST_NAME
import com.mvvmexample.apimvvmclean.common.util.Constants.IMAGE
import com.mvvmexample.apimvvmclean.common.util.Constants.LAST_NAME
import com.mvvmexample.apimvvmclean.common.util.Constants.PREFS_NAME
import com.mvvmexample.apimvvmclean.common.util.Constants.REFRESH_TOKEN
import com.mvvmexample.apimvvmclean.common.util.Constants.USERNAME
import com.mvvmexample.apimvvmclean.data.modelDto.LoginResponseDto
import javax.inject.Inject

class AuthPreferences @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveAuthToken(userAuth: LoginResponseDto) {
        sharedPreferences.edit().apply {
            putString(ACCESS_TOKEN, userAuth.accessToken)
            putString(REFRESH_TOKEN, userAuth.refreshToken)
            putString(USERNAME, userAuth.username)
            putString(FIRST_NAME, userAuth.firstName)
            putString(LAST_NAME, userAuth.lastName)
            putString(EMAIL, userAuth.email)
            putString(IMAGE, userAuth.image)
            apply()
        }
    }

    fun getAuthToken(): String? = sharedPreferences.getString(ACCESS_TOKEN, null)
    fun getRefreshToken(): String? = sharedPreferences.getString(REFRESH_TOKEN, null)
    fun getUsername(): String? = sharedPreferences.getString(USERNAME, null)
    fun getFirstName(): String? = sharedPreferences.getString(FIRST_NAME, null)
    fun getLastName(): String? = sharedPreferences.getString(LAST_NAME, null)
    fun getEmail(): String? = sharedPreferences.getString(EMAIL, null)
    fun getImage(): String? = sharedPreferences.getString(IMAGE, null)
    
    fun clearAuthToken() {
        sharedPreferences.edit().apply {
            remove(ACCESS_TOKEN)
            remove(REFRESH_TOKEN)
            remove(USERNAME)
            remove(FIRST_NAME)
            remove(LAST_NAME)
            remove(EMAIL)
            remove(IMAGE)
            apply()
        }
    }

    fun isAuthenticated(): Boolean {
        return getAuthToken() != null
    }
}
