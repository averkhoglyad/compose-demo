package stepic.vk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import stepic.vk.layout.view.FavoritesView
import stepic.vk.layout.view.FeedView
import stepic.vk.layout.view.ProfileView
import stepic.vk.model.VkViewModel

data class FeedScreen(val viewModel: VkViewModel) : ModifiableScreen() {

    override val key = "FeedScreen"

    @Composable
    override fun Content() {
        FeedView(viewModel, modifier = this.modifier)
    }
}

object FavoritesScreen : ModifiableScreen() {
    override val key = "FavoritesScreen"

    @Composable
    override fun Content() {
        FavoritesView(modifier = this.modifier)
    }
}

object ProfileScreen : ModifiableScreen() {
    override val key = "ProfileScreen"

    @Composable
    override fun Content() {
        ProfileView(modifier = this.modifier)
    }
}

abstract class ModifiableScreen : Screen {

    var modifier: Modifier = Modifier

}
