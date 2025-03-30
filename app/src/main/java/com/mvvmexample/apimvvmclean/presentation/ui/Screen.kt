package com.mvvmexample.apimvvmclean.presentation.ui


sealed class Screen(val route: String) {
    data object Users : Screen("users")
    data object UserDetail : Screen("user_detail/{id}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }
}