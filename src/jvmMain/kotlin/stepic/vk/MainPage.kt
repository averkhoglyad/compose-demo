package stepic.vk

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import stepic.vk.data.MetricItem
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import stepic.vk.model.FeedViewModel
import java.net.URI
import java.time.Instant

@Composable
fun MainPage(postModel: FeedViewModel) {
    Scaffold(
        bottomBar = { bottomNavigation() },
    ) {
        PostCard(
            post = postModel.post,
            modifier = Modifier.padding(8.dp),
            onViewsClick = postModel::incMetric,
            onCommentsClick = postModel::incMetric,
            onLikeClick = postModel::incMetric,
            onSharesClick = postModel::incMetric,
        )
    }
}

@Composable
private fun bottomNavigation() {
    var selected by remember { mutableStateOf(BottomNavItems.HOME) }

    BottomNavigation {
        BottomNavItems.values()
            .forEach {
                BottomNavigationItem(
                    selected = it == selected,
                    onClick = { selected = it },
                    icon = {
                        Icon(imageVector = it.icon, contentDescription = it.title)
                    },
                    label = {
                        Text(it.title)
                    },
                    selectedContentColor = MaterialTheme.colors.onPrimary,
                    unselectedContentColor = MaterialTheme.colors.onSecondary,
                )
            }
    }
}

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

fun main() = application {
    val postModel = FeedViewModel(VkPost(
        community = "/dev/null",
        avatar = URI("/vk/dev-null.jpg"),
        contentText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        contentImage = URI("/vk/post-img.jpg"),
        publishedAt = Instant.now(),
        metrics = listOf(
            MetricItem(MetricType.LIKES, 12),
            MetricItem(MetricType.VIEWS, 628),
            MetricItem(MetricType.SHARES, 112),
            MetricItem(MetricType.COMMENTS, 29),
        )
    ))

    Window(onCloseRequest = ::exitApplication) {
        VkProjectTheme {
            MainPage(postModel)
        }
    }
}
