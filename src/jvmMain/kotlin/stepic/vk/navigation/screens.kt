package stepic.vk.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.*
import stepic.vk.data.VkPost
import stepic.vk.layout.view.FavoritesView
import stepic.vk.layout.view.HomeView
import stepic.vk.layout.view.ProfileView
import stepic.vk.layout.view.feed.CommentsView

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
            HomeView(modifier = this.modifier, onShowCommentsClick = { navState.goTo(CommentsScreen(it)) })
        }
    }

    class CommentsScreen(private val post: VkPost) : ModifiableScreen() {

        override val key = "${HomeScreen.key}.CommentsScreen"

        @Composable
        override fun Content() {
            val navState = rememberNavState()
            CommentsView(post, modifier = this.modifier, onBackClick = { navState.goBack() })
        }
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

@Composable
fun CurrentScreen(modifier: Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    val currentScreen = navigator.lastItem as? ModifiableScreen
    currentScreen?.modifier = modifier
    CurrentScreen()
}

