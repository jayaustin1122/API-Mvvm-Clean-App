package com.mvvmexample.apimvvmclean.presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mvvmexample.apimvvmclean.domain.model.ListPosts
import com.mvvmexample.apimvvmclean.domain.model.Post

@Composable
fun HomeScreen(
    viewModel: PostsViewModel = hiltViewModel()
) {
    val postsState by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getAllPosts()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF0F2F5) // Facebook background color
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // App Bar
            AppBar()

            // Content
            when {
                postsState.isLoading -> {
                    LoadingView()
                }
                postsState.error.isNotBlank() -> {
                    ErrorView(postsState.error)
                }
                postsState.posts.isNotEmpty() -> {
                    PostList(posts = postsState.posts)
                }
                else -> {
                    EmptyView()
                }
            }
        }
    }
}

@Composable
fun AppBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "SocialFeed",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color(0xFF1877F2) // Facebook blue
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.Gray
                )
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Messages",
                    tint = Color.Gray
                )
            }
        }
    }
}

@Composable
fun PostList(posts: List<ListPosts>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(posts) { post ->
            PostCard(post = post)
        }
    }
}

@Composable
fun PostCard(post: ListPosts) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // User info header
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User avatar placeholder
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF1877F2))
                ) {
                    Text(
                        text = "U${post.userID}",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = "User ${post.userID}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "${post.views} views",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Post title
            Text(
                text = post.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Post body
            Text(
                text = post.body,
                fontSize = 14.sp,
                color = Color.DarkGray,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tags
            if (post.tags.isNotEmpty()) {
                TagsRow(tags = post.tags)
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Reactions count
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Likes",
                        tint = Color(0xFF1877F2),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.reactions.likes}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Dislikes",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${post.reactions.dislikes}",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = "${post.views} views",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                color = Color.LightGray
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ActionButton(
                    icon = Icons.Default.ThumbUp,
                    text = "Like",
                    onClick = { /* Handle like action */ }
                )

                ActionButton(
                    icon = Icons.Default.Email,
                    text = "Comment",
                    onClick = { /* Handle comment action */ }
                )

                ActionButton(
                    icon = Icons.Default.Share,
                    text = "Share",
                    onClick = { /* Handle share action */ }
                )
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

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = Color(0xFF1877F2)
        )
    }
}

@Composable
fun ErrorView(errorMessage: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "Error",
            tint = Color.Red,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Something went wrong",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = errorMessage,
            color = Color.Gray,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Handle retry */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1877F2)
            )
        ) {
            Text("Retry")
        }
    }
}

@Composable
fun EmptyView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "No posts",
            tint = Color.Gray,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "No posts found",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}