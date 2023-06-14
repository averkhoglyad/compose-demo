package stepic.vk.layout.view.feed

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import stepic.vk.data.VkPost
import stepic.vk.layout.component.PostCard
import stepic.vk.model.PostsFeedViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun FeedPost(posts: List<VkPost>,
             viewModel: PostsFeedViewModel,
             modifier: Modifier = Modifier,
             onShowCommentsClick: (VkPost) -> Unit) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = posts, key = VkPost::id) { post ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                viewModel.drop(post)
            }

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),
                modifier = Modifier.animateItemPlacement(),
                background = {}
            ) {
                PostCard(
                    post = post,
                    onViewsClick = { viewModel.incMetric(post, it.type) },
                    onCommentsClick = { onShowCommentsClick(post) },
                    onLikeClick = { viewModel.incMetric(post, it.type) },
                    onSharesClick = { viewModel.incMetric(post, it.type) },
                )
            }
        }
    }
}
