package com.mvvmexample.apimvvmclean.presentation.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LogInScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val isAuthenticated = viewModel.isAuthenticated()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            onLoginSuccess()
        }
    }
    if (state.isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return
    }
    LaunchedEffect(state.user) {
        if (state.user != null) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.login(username, password) },
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text(text = if (state.isLoading) "Logging in..." else "Login")
        }

        state.error?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }
    }
}
