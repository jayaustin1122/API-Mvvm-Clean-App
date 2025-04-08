package com.mvvmexample.apimvvmclean.data.local

import android.content.Context
import android.content.SharedPreferences
import com.mvvmexample.apimvvmclean.data.model.LoginResponseDto
import javax.inject.Inject

class AuthPreferences @Inject constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun saveAuthToken(userAuth: LoginResponseDto) {
        sharedPreferences.edit().apply {
            putString("access_token", userAuth.accessToken)
            putString("refresh_token", userAuth.refreshToken)
            putString("username", userAuth.username)
            putString("first_name", userAuth.firstName)
            putString("last_name", userAuth.lastName)
            putString("email", userAuth.email)
            putString("image", userAuth.image)
            apply()
        }
    }

    fun getAuthToken(): String? = sharedPreferences.getString("access_token", null)
    fun getRefreshToken(): String? = sharedPreferences.getString("refresh_token", null)
    fun getUsername(): String? = sharedPreferences.getString("username", null)
    fun getFirstName(): String? = sharedPreferences.getString("first_name", null)
    fun getLastName(): String? = sharedPreferences.getString("last_name", null)
    fun getEmail(): String? = sharedPreferences.getString("email", null)
    fun getImage(): String? = sharedPreferences.getString("image", null)


    fun clearAuthToken() {
        sharedPreferences.edit().apply {
            remove("access_token")
            remove("refresh_token")
            remove("username")
            remove("first_name")
            remove("last_name")
            remove("email")
            remove("image")
            apply()
        }
    }

    fun isAuthenticated(): Boolean {
        return getAuthToken() != null
    }
}
