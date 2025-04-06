package com.mvvmexample.apimvvmclean.presentation.ui


sealed class Screen(val route: String) {
    data object Users : Screen("users")
    data object UserDetail : Screen("user_detail/{id}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }

    data object Home : Screen("home")
    data object Settings : Screen("settings")
    data object Profile : Screen("profile")
    data object Login : Screen("login")
}