package stepic.vk.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import stepic.vk.data.VkPost
import stepic.vk.layout.view.FavoritesView
import stepic.vk.layout.view.HomeView
import stepic.vk.layout.view.ProfileView
import stepic.vk.layout.view.feed.CommentsView

class HomeScreen : ModifiableScreen() {

    override val key = "HomeScreen"

    @Composable
    override fun Content() {
        var selectedPost by remember { mutableStateOf<VkPost?>(null) }
        selectedPost
            ?.let { CommentsView(it, modifier = this.modifier, onBackClick = { selectedPost = null }) }
            ?: HomeView(modifier = this.modifier, onShowCommentsClick = { selectedPost = it })
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
