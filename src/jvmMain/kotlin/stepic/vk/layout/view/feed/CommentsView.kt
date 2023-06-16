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

    val screenState = viewModel.screenStateState
    when (val state = screenState.value) {
        CommentsScreenState.Initial -> {}
        is CommentsScreenState.Comments -> {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text("Comments for Post #${state.post.id}")
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
                    items(items = state.comments, key = VkPostComment::id) {
                        CommentItem(it)
                    }
                }
            }
        }
    }
}
