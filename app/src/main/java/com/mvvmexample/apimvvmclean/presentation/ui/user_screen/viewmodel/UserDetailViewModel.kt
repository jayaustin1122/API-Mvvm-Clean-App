package com.mvvmexample.apimvvmclean.presentation.ui.user_screen.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmexample.apimvvmclean.domain.usecase.GetUserByIdUseCase
import com.mvvmexample.apimvvmclean.presentation.state.UserDetailState
import com.mvvmexample.apimvvmclean.common.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(UserDetailState())
    val state: StateFlow<UserDetailState> = _state

    init {
        savedStateHandle.get<Int>("id")?.let { userId ->
            getUserById(userId)
        }
    }

    private fun getUserById(id: Int) {
        getUserByIdUseCase(id).onEach { result ->
            when (result) {
                is Response.Loading -> {
                    _state.value = UserDetailState(isLoading = true)
                }
                is Response.Success -> {
                    _state.value = UserDetailState(user = result.data)
                }
                is Response.Error -> {
                    _state.value = UserDetailState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}