package com.mvvmexample.apimvvmclean.presentation.ui

import com.google.gson.Gson
import com.mvvmexample.apimvvmclean.common.util.Constants
import com.mvvmexample.apimvvmclean.data.modelDto.LoginResponseDto
import java.net.URLEncoder

sealed class Screens(val route: String) {
    data object Users : Screens(Constants.USERS)
    data object UserDetail : Screens("${Constants.USER_DETAIL}/{id}") {
        fun createRoute(userId: Int) = "${Constants.USER_DETAIL}/${userId}"
    }

    data object Home : Screens(Constants.HOME)
    data object Settings : Screens(Constants.SETTINGS)
    data object Profile : Screens(Constants.PROFILE)
    data object Auth : Screens(Constants.AUTH)
    data object EditProfile {
        const val route = "edit_profile"
        fun createRoute(editProfile: LoginResponseDto): String {
            val json = URLEncoder.encode(Gson().toJson(editProfile), "UTF-8")
            return "$route?user=$json"
        }
    }
}