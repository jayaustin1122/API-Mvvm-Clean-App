package com.mvvmexample.apimvvmclean.presentation.ui.user_screen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmexample.apimvvmclean.domain.usecase.GetUsersUseCase
import com.mvvmexample.apimvvmclean.presentation.state.UsersState
import com.mvvmexample.apimvvmclean.common.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UsersState())
    val state: StateFlow<UsersState> = _state

    init {
        getUsers()
    }

    fun getUsers() {
        getUsersUseCase().onEach { result ->
            when (result) {
                is Response.Loading -> {
                    _state.value = UsersState(isLoading = true)
                }
                is Response.Success -> {
                    _state.value = UsersState(users = result.data ?: emptyList())
                }
                is Response.Error -> {
                    _state.value = UsersState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}