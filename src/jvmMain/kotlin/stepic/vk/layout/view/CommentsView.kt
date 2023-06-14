package stepic.vk.layout.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import stepic.vk.data.VkPost
import stepic.vk.data.VkPostComment
import stepic.vk.layout.component.CommentItem

@Composable
fun CommentsView(post: VkPost,
                 comments: List<VkPostComment>,
                 modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Comments for Post #${post.id}")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
            )
        }
    ) { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(padding)
        ) {
            items(items = comments, key = VkPostComment::id) {
                CommentItem(it)
            }
        }
    }
}
