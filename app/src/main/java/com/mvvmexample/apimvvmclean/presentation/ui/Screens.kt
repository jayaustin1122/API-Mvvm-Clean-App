package com.mvvmexample.apimvvmclean.presentation.ui

import com.mvvmexample.apimvvmclean.common.util.Constants

sealed class Screens(val route: String) {
    data object Users : Screens(Constants.USERS)
    data object UserDetail : Screens("${Constants.USER_DETAIL}/{id}") {
        fun createRoute(userId: Int) = "${Constants.USER_DETAIL}/${userId}"
    }

    data object Home : Screens(Constants.HOME)
    data object Settings : Screens(Constants.SETTINGS)
    data object Profile : Screens(Constants.PROFILE)
    data object Login : Screens(Constants.LOGIN)
}