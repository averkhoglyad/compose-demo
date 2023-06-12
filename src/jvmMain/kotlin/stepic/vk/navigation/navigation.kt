package stepic.vk.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import stepic.vk.model.VkViewModel
import kotlin.reflect.KClass

interface NavItem<T: Screen> {
    val title: String
    val icon: ImageVector
    val screenType: KClass<out T>

    fun screen(viewModel: VkViewModel): T
}

enum class BottomNavItems(override val title: String,
                          override val icon: ImageVector,
                          override val screenType: KClass<out Screen>) : NavItem<Screen> {

    HOME(title = "Home", icon = Icons.Default.Home, screenType = FeedScreen::class) {
        override fun screen(viewModel: VkViewModel): Screen = FeedScreen(viewModel)
    },
    FAVORITE(title = "Favorite", icon = Icons.Default.Favorite, screenType = FavoritesScreen::class) {
        override fun screen(viewModel: VkViewModel): Screen = FavoritesScreen
    },
    PROFILE(title = "Profile", icon = Icons.Default.Person, screenType = ProfileScreen::class) {
        override fun screen(viewModel: VkViewModel): Screen = ProfileScreen
    }
}
