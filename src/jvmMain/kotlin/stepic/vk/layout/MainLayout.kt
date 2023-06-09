package stepic.vk.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import stepic.vk.layout.component.TextCountable
import stepic.vk.layout.view.FavoritesView
import stepic.vk.layout.view.HomeView
import stepic.vk.layout.view.ProfileView
import stepic.vk.model.BottomNavItems
import stepic.vk.model.NavItem
import stepic.vk.model.VkViewModel

@Composable
fun MainLayout(viewModel: VkViewModel) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(10.dp),
        bottomBar = {
            BottomNavigationMenu(viewModel.currentNavItemState, viewModel::selectNavItem)
        },
    ) { paddingValues ->
        val modifier = Modifier.padding(paddingValues).padding(5.dp).padding(bottom = 10.dp)
        when (viewModel.currentNavItem) {
            BottomNavItems.HOME -> HomeView(viewModel, modifier = modifier)
            BottomNavItems.FAVORITE -> FavoritesView(modifier = modifier)
            BottomNavItems.PROFILE -> ProfileView(modifier = modifier)
        }
    }
}

@Composable
private fun BottomNavigationMenu(currentNavItemState: State<NavItem>, onNavItemClick: (NavItem) -> Unit = {}) {
    BottomNavigation {
        BottomNavItems.values()
            .forEach {
                BottomNavigationItem(
                    selected = it == currentNavItemState.value,
                    onClick = { onNavItemClick(it) },
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
