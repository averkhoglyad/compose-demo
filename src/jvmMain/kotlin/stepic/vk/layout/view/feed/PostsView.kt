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
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import stepic.vk.layout.component.PostCard
import stepic.vk.model.PostScreenState
import stepic.vk.model.PostsViewModel

@Composable
fun PostsView(viewModel: PostsViewModel,
              modifier: Modifier = Modifier,
              onShowCommentsClick: (VkPost) -> Unit = {}) {
    when (val state = viewModel.screenState) {
        is PostScreenState.Initial -> {}
        is PostScreenState.Posts ->
            PostsList(
                posts = state.posts,
                modifier = modifier,
                onShowCommentsClick = onShowCommentsClick,
                onMetricClick = viewModel::incMetric,
                onDropPost = viewModel::drop)
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun PostsList(posts: List<VkPost>,
                      modifier: Modifier = Modifier,
                      onShowCommentsClick: (VkPost) -> Unit,
                      onDropPost: (VkPost) -> Unit,
                      onMetricClick: (VkPost, MetricType) -> Unit) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = posts, key = VkPost::id) { post ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                onDropPost(post)
            }

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.EndToStart),
                modifier = Modifier.animateItemPlacement(),
                background = {}
            ) {
                PostCard(
                    post = post,
                    onViewsClick = { onMetricClick(post, it.type) },
                    onCommentsClick = { onShowCommentsClick(post) },
                    onLikeClick = { onMetricClick(post, it.type) },
                    onSharesClick = { onMetricClick(post, it.type) },
                )
            }
        }
    }
}
