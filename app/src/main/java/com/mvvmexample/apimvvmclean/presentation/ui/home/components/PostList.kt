package com.mvvmexample.apimvvmclean.presentation.ui.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mvvmexample.apimvvmclean.domain.model.Comment
import com.mvvmexample.apimvvmclean.domain.model.ListPosts

@Composable
fun PostList(
    posts: List<ListPosts>,
    selectedPostId: Long?,
    comment: List<Comment>,
    isLoadingComments: Boolean,
    onPostClick: (Long) -> Unit,
    onLikeClick: (Long) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(posts) { post ->
            PostCard(
                post = post,
                isSelected = post.id == selectedPostId,
                comment = if (post.id == selectedPostId) comment else null,
                isLoadingComments = isLoadingComments && post.id == selectedPostId,
                onPostClick = { onPostClick(post.id) },
                onLikeClick = { onLikeClick(post.id) }
            )
        }
    }
}