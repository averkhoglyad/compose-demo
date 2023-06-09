package stepic.vk.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

interface NavItem {
    val title: String
    val icon: ImageVector
}

enum class BottomNavItems(
    override val title: String,
    override val icon: ImageVector
) : NavItem {

    HOME("Home", Icons.Default.Home),
    FAVORITE("Favorite", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.Person)

}
