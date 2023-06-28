package stepic.vk.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.Navigator
import stepic.vk.data.VkPost
import stepic.vk.navigation.rememberNavState
import stepic.vk.representation.view.home.comments.CommentsView
import stepic.vk.representation.view.home.posts.PostsView
import stepic.vk.representation.view.home.comments.CommentsViewModel
import stepic.vk.representation.view.home.posts.PostsViewModel

object HomeScreen : ModifiableScreen() {

    override val key = "HomeScreen"

    @Composable
    override fun Content() {
        Navigator(PostsScreen) {
            CurrentScreen(this.modifier)
        }
    }

    object PostsScreen : ModifiableScreen() {

        override val key = "${HomeScreen.key}.PostsScreen"

        @Composable
        override fun Content() {
            val navState = rememberNavState()
            val model = rememberScreenModel { PostsViewModel(7).apply { loadPosts() } }
            PostsView(
                viewModel = model,
                modifier = this.modifier,
                onShowCommentsClick = { navState.goTo(CommentsScreen(it)) }
            )
        }
    }

    class CommentsScreen(private val post: VkPost) : ModifiableScreen() {

        override val key = "${HomeScreen.key}.CommentsScreen"

        @Composable
        override fun Content() {
            val navState = rememberNavState()
            val model = rememberScreenModel { CommentsViewModel().apply { loadComments(post) } }
            CommentsView(
                viewModel = model,
                modifier = this.modifier,
                onBackClick = { navState.goBack() }
            )
        }
    }
}