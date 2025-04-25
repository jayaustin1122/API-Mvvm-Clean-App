package com.mvvmexample.apimvvmclean.presentation.ui.user_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvmexample.apimvvmclean.common.composables.ErrorView
import com.mvvmexample.apimvvmclean.common.composables.LoadingView
import com.mvvmexample.apimvvmclean.common.util.ThemeToggleButton
import com.mvvmexample.apimvvmclean.domain.model.User
import com.mvvmexample.apimvvmclean.presentation.ui.user_screen.viewmodel.UsersViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(
    viewModel: UsersViewModel = hiltViewModel(),
    onUserClick: (Int) -> Unit,
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users") }
            )
            ThemeToggleButton(
                isDarkTheme = isDarkTheme,
                onThemeToggle = onThemeToggle
            )

        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.isLoading) {
                LoadingView()
            } else if (state.error.isNotEmpty()) {
                ErrorView(
                    onClickRetry = { viewModel.getUsers() }, state.error
                )
            } else {
                UserList(
                    isDarkTheme,
                    onThemeToggle,
                    users = state.users,
                    onUserClick = onUserClick
                )
            }
        }
    }
}

@Composable
fun UserList(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    users: List<User>,
    onUserClick: (Int) -> Unit
) {
    ThemeToggleButton(
        isDarkTheme = isDarkTheme,
        onThemeToggle = onThemeToggle
    )
    LazyColumn {
        items(users) { user ->
            UserItem(
                user = user,
                onUserClick = { onUserClick(user.id) }
            )
        }
    }
}

@Composable
fun UserItem(
    user: User,
    onUserClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onUserClick() },
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.email,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = user.phone,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}