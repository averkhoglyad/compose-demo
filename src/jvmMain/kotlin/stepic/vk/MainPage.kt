package stepic.vk

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import stepic.vk.data.VkPost
import stepic.vk.model.FeedViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainPage(feedViewModel: FeedViewModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(10.dp),
        bottomBar = { bottomNavigation() },
    ) {
        LazyColumn(
            contentPadding = PaddingValues(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 72.dp),
            verticalArrangement = spacedBy(8.dp),
            modifier = Modifier.padding(it)
        ) {
            items(items = feedViewModel.posts, key = VkPost::id) { post ->
                val dismissState = rememberDismissState()
                if (dismissState.isDismissed(DismissDirection.EndToStart)) {
                    feedViewModel.drop(post)
                }

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    modifier = Modifier.animateItemPlacement(),
                    background = {}
                ) {
                    PostCard(
                        post = post,
                        onViewsClick = { feedViewModel.incMetric(post, it.type) },
                        onCommentsClick = { feedViewModel.incMetric(post, it.type) },
                        onLikeClick = { feedViewModel.incMetric(post, it.type) },
                        onSharesClick = { feedViewModel.incMetric(post, it.type) },
                    )
                }
            }
        }
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
    Window(onCloseRequest = ::exitApplication) {
        VkProjectTheme {
            MainPage(FeedViewModel(7))
        }
    }
}
