package stepic.vk.representation.view.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import stepic.vk.navigation.screen.FavoritesScreen
import stepic.vk.navigation.screen.HomeScreen
import stepic.vk.navigation.screen.ProfileScreen
import kotlin.reflect.KClass

interface NavItem {
    val title: String
    val icon: ImageVector
    val screenType: KClass<out Screen>

    fun screen(): Screen
}

enum class BottomNavItems(override val title: String,
                          override val icon: ImageVector,
                          override val screenType: KClass<out Screen>) : NavItem {
    HOME(title = "Home", icon = Icons.Default.Home, screenType = HomeScreen::class) {
        override fun screen(): Screen = HomeScreen
    },
    FAVORITE(title = "Favorite", icon = Icons.Default.Favorite, screenType = FavoritesScreen::class) {
        override fun screen(): Screen = FavoritesScreen
    },
    PROFILE(title = "Profile", icon = Icons.Default.Person, screenType = ProfileScreen::class) {
        override fun screen(): Screen = ProfileScreen
    }
}
