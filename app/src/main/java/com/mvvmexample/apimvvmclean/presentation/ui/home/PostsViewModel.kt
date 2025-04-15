package com.mvvmexample.apimvvmclean.presentation.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvmexample.apimvvmclean.domain.usecase.GetAllPostsUseCase
import com.mvvmexample.apimvvmclean.domain.usecase.GetCommentByIdUseCase
import com.mvvmexample.apimvvmclean.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getAllPostsUseCase: GetAllPostsUseCase,
    private val getCommentByIdUseCase: GetCommentByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(PostState())
    val state: StateFlow<PostState> = _state


    init {
        getAllPosts()
    }

    fun getAllPosts() {
        getAllPostsUseCase().onEach { result ->
            when (result) {
                is Response.Loading -> {
                    _state.value = PostState(isLoading = true)
                }

                is Response.Success -> {
                    _state.value = PostState(posts = result.data ?: emptyList())
                    Log.d("PostsViewModel", "PostsViewModel: ${result.data}")
                }

                is Response.Error -> {
                    _state.value =
                        PostState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun likePost(postId: Long) {
        viewModelScope.launch {
            val updatedPosts = _state.value.posts.map { post ->
                if (post.id == postId) {
                    post.copy(
                        reactions = post.reactions.copy(
                            likes = post.reactions.likes + 1
                        )
                    )
                } else {
                    post
                }
            }
            _state.update { it.copy(posts = updatedPosts) }
        }
    }

    fun dislikePost(postId: Long) {
        viewModelScope.launch {
            val updatedPosts = _state.value.posts.map { post ->
                if (post.id == postId) {
                    post.copy(
                        reactions = post.reactions.copy(
                            dislikes = post.reactions.dislikes + 1
                        )
                    )
                } else {
                    post
                }
            }
            _state.update { it.copy(posts = updatedPosts) }
        }
    }

    fun toggleCommentVisibility(postId: Long?) {
        Log.d("post","post : $postId")
        _state.update { currentState ->
            if (currentState.selectedPostId == postId) {
                currentState.copy(selectedPostId = null, comment = null)
            } else {
                currentState.copy(selectedPostId = postId)
            }
        }

        if (postId != null) {

            getCommentById(postId.toInt())
        }
    }

    fun getCommentById(id: Int) {
        _state.update { it.copy(isLoadingComments = true) }

        getCommentByIdUseCase(id).onEach { result ->
            when (result) {
                is Response.Loading -> {
                    _state.update { it.copy(isLoadingComments = true) }
                }

                is Response.Success -> {
                    Log.d("post","comment: $id")
                    _state.update { it.copy(
                        isLoadingComments = false,
                        comment = result.data,
                        error = ""
                    ) }
                }

                is Response.Error -> {
                    _state.update { it.copy(
                        isLoadingComments = false,
                        error = result.message ?: "Failed to load comments"
                    ) }
                }
            }
        }.launchIn(viewModelScope)
    }
}