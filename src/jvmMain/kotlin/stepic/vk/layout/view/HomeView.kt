package stepic.vk.layout.view
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepic.vk.data.VkPost
import stepic.vk.layout.view.feed.FeedPost
import stepic.vk.model.PostScreenState
import stepic.vk.model.PostsFeedViewModel

@Composable
fun HomeView(modifier: Modifier = Modifier,
             onShowCommentsClick: (VkPost) -> Unit = {}) {

    val viewModel = PostsFeedViewModel(7) // TODO: Find a way to inject it

    when (val state = viewModel.screenState) {
        PostScreenState.Initial -> {}
        is PostScreenState.Posts ->
            FeedPost(state.posts, viewModel, modifier, onShowCommentsClick)
    }
}
