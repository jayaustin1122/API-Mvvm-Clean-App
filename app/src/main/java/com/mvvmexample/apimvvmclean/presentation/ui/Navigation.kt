package com.mvvmexample.apimvvmclean.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mvvmexample.apimvvmclean.presentation.ui.auth.AuthScreen
import com.mvvmexample.apimvvmclean.presentation.ui.home.HomeScreen
import com.mvvmexample.apimvvmclean.presentation.ui.profile.ProfileScreen
import com.mvvmexample.apimvvmclean.presentation.ui.user_screen.UserDetailScreen
import com.mvvmexample.apimvvmclean.presentation.ui.user_screen.UsersScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    var isDarkTheme by remember { mutableStateOf(false) }

    val bottomBarScreens = listOf(
        Screens.Home.route,
        Screens.Users.route,
        Screens.Settings.route,
        Screens.Profile.route
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarScreens) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Auth.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screens.Users.route) {
                UsersScreen(
                    isDarkTheme = isDarkTheme,
                    onThemeToggle = { isDarkTheme = it },
                    onUserClick = { id ->
                        navController.navigate(Screens.UserDetail.createRoute(id))
                    }
                )
            }

            composable(Screens.Home.route) {
                HomeScreen()
            }

            composable(
                route = Screens.UserDetail.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                UserDetailScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screens.Settings.route) {
                SettingsScreen()
            }

            composable(Screens.Profile.route) {
                ProfileScreen(
                    onLogoutSuccess = {
                        navController.navigate(Screens.Auth.route) {
                            popUpTo(Screens.Profile.route) { inclusive = true }
                        }
                    }
                )
            }

            composable(Screens.Auth.route) {
                AuthScreen(
                    onLoginSuccess = {
                        navController.navigate(Screens.Home.route) {
                            popUpTo(Screens.Auth.route) { inclusive = true }
                        }
                    }
                )
            }

        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Screens.Home to Icons.Default.Home,
        Screens.Users to Icons.AutoMirrored.Filled.List,
        Screens.Settings to Icons.Default.Settings,
        Screens.Profile to Icons.Default.Person
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { (screen, icon) ->
            NavigationBarItem(
                icon = { Icon(imageVector = icon, contentDescription = screen.route) },
                label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun SettingsScreen() {
    Text("Settings Screen")
}