package com.mvvmexample.apimvvmclean.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvmexample.apimvvmclean.common.composables.EmptyView
import com.mvvmexample.apimvvmclean.common.composables.ErrorView
import com.mvvmexample.apimvvmclean.common.composables.LoadingView
import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.domain.model.Reactions
import com.mvvmexample.apimvvmclean.presentation.ui.home.components.AddPost
import com.mvvmexample.apimvvmclean.presentation.ui.home.components.AppBar
import com.mvvmexample.apimvvmclean.presentation.ui.home.components.PostList

@Composable
fun HomeScreen(
    viewModel: PostsViewModel = hiltViewModel()
) {
    val postsState by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F2F5)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AppBar()

            when {
                postsState.isLoading -> {
                    LoadingView()
                }

                postsState.error.isNotBlank() -> {
                    ErrorView(onClickRetry = { viewModel.getAllPosts() },postsState.error)
                }

                postsState.posts.isNotEmpty() -> {
                    postsState.comment?.let {
                        AddPost(
                            onAddPost = { title, body, tags ->
                                val post = ListPosts(
                                    id = System.currentTimeMillis(),
                                    title = title,
                                    body = body,
                                    tags = tags,
                                    reactions = Reactions(0, 0),
                                    views = 0,
                                    userID = 1L
                                )
                                viewModel.addPost(post)
                            }
                        )
                        PostList(
                            posts = postsState.posts,
                            selectedPostId = postsState.selectedPostId,
                            comment = it,
                            isLoadingComments = postsState.isLoadingComments,
                            onPostClick = { postId -> viewModel.toggleCommentVisibility(postId) },
                            onLikeClick = { postId -> viewModel.likePost(postId) }
                        )
                    }
                }

                else -> {
                    EmptyView("No posts available please check your internet connection")
                }
            }
        }
    }
}

@Composable
fun TagsRow(tags: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        tags.take(3).forEach { tag ->
            Chip(tag = tag)
            Spacer(modifier = Modifier.width(8.dp))
        }

        if (tags.size > 3) {
            Text(
                text = "+${tags.size - 3} more",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun Chip(tag: String) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE4E6EB)
    ) {
        Text(
            text = "#$tag",
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = Color(0xFF1877F2),
            fontSize = 12.sp
        )
    }
}

@Composable
fun ActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Gray
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}


