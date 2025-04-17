package com.mvvmexample.apimvvmclean.presentation.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvmexample.apimvvmclean.common.util.dialog.AppDialog
import com.mvvmexample.apimvvmclean.common.util.dialog.DialogData
import com.mvvmexample.apimvvmclean.common.util.dialog.DialogUtils
import com.mvvmexample.apimvvmclean.presentation.ui.profile.components.ProfileContent

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onLogoutSuccess: () -> Unit
) {
    val scrollState = rememberScrollState()
    val profileState by viewModel.profileState.collectAsState()

    var showLogoutDialog by remember { mutableStateOf<DialogData?>(null) }
    var isLoggingOut by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.loadUserProfile()
    }

    LaunchedEffect(isLoggingOut) {
        if (isLoggingOut) {
            onLogoutSuccess()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when {
            profileState.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            profileState.error.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = profileState.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            profileState.userProfile != null -> {
                val user = profileState.userProfile!!
                ProfileContent(
                    user = user,
                    onLogout = {
                        showLogoutDialog = DialogUtils.createLogoutConfirmationDialog(
                            onConfirm = {
                                viewModel.logout()
                                isLoggingOut = true
                                showLogoutDialog = null
                            },
                            onCancel = {
                                showLogoutDialog = null
                            }
                        )
                    }
                )
            }
        }

        showLogoutDialog?.let {
            AppDialog(
                dialogData = it,
                onDismissRequest = { showLogoutDialog = null }
            )
        }
    }
}