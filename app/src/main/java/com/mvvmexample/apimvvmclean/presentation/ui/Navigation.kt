package com.mvvmexample.apimvvmclean.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()

    var isDarkTheme by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = Screen.Users.route
    ) {
        composable(Screen.Users.route) {
            UsersScreen(
                isDarkTheme = isDarkTheme,
                onThemeToggle = { isDarkTheme = it },
                onUserClick = { id ->
                    navController.navigate(Screen.UserDetail.createRoute(id))
                }
            )
        }

        composable(
            route = Screen.UserDetail.route,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) {
            UserDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
