package com.mvvmexample.apimvvmclean.presentation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmexample.apimvvmclean.data.local.AuthPreferences
import com.mvvmexample.apimvvmclean.data.modelDto.LoginResponseDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authPreferences: AuthPreferences
) : ViewModel() {

    private val _profileState = MutableStateFlow(ProfileState())
    val profileState: StateFlow<ProfileState> = _profileState

    fun loadUserProfile() {
        viewModelScope.launch {
            try {
                _profileState.value = ProfileState(isLoading = true)

                val username = authPreferences.getUsername()
                if (username != null) {
                    val userProfile = LoginResponseDto(
                        username = username,
                        email = authPreferences.getEmail() ?: "",
                        image = authPreferences.getImage() ?: "",
                        firstName = authPreferences.getFirstName() ?: "",
                        lastName = authPreferences.getLastName() ?: "",
                        id = 0,
                        gender = "",
                        accessToken = authPreferences.getAuthToken() ?: "",
                        refreshToken = authPreferences.getRefreshToken() ?: ""
                    )

                    _profileState.value = ProfileState(
                        isLoading = false,
                        userProfile = userProfile
                    )
                } else {
                    _profileState.value = ProfileState(
                        isLoading = false,
                        error = "User not logged in"
                    )
                }
            } catch (e: Exception) {
                _profileState.value = ProfileState(
                    isLoading = false,
                    error = e.message ?: "Unknown error occurred"
                )
            }
        }
    }

    fun logout() {
        authPreferences.clearAuthToken()
    }
}
