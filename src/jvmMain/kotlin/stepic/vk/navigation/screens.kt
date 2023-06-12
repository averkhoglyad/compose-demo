package stepic.vk.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import stepic.vk.layout.view.FavoritesView
import stepic.vk.layout.view.FeedView
import stepic.vk.layout.view.ProfileView
import stepic.vk.model.VkViewModel

data class FeedScreen(val viewModel: VkViewModel) : Screen {

    override val key = "FeedScreen"

    @Composable
    override fun Content() {
        FeedView(viewModel)
    }
}

object FavoritesScreen : Screen {
    override val key = "FavoritesScreen"

    @Composable
    override fun Content() {
        FavoritesView()
    }
}

object ProfileScreen : Screen {
    override val key = "ProfileScreen"

    @Composable
    override fun Content() {
        ProfileView()
    }
}
