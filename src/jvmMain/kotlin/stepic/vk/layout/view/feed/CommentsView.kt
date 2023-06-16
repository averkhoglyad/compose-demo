package stepic.vk.layout.view.feed

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
import stepic.vk.model.CommentsScreenState
import stepic.vk.model.CommentsViewModel

@Composable
fun CommentsView(viewModel: CommentsViewModel,
                 modifier: Modifier = Modifier,
                 onBackClick: () -> Unit = {}) {

    when (val state = viewModel.screenState) {
        is CommentsScreenState.Initial -> {}
        is CommentsScreenState.Comments -> {
            CommentsList(state.post, state.comments, modifier, onBackClick)
        }
    }
}

@Composable
private fun CommentsList(post: VkPost,
                         comments: List<VkPostComment>,
                         modifier: Modifier,
                         onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Comments for Post #${post.id}")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
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
