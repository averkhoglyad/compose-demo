package stepic.vk.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import stepic.vk.navigation.BottomNavItems
import stepic.vk.model.VkViewModel
import stepic.vk.navigation.FeedScreen
import stepic.vk.navigation.ModifiableScreen
import stepic.vk.navigation.rememberNavState

@Composable
fun MainLayout(viewModel: VkViewModel) {
    Navigator(FeedScreen(viewModel)) { navigator ->
        val navState = rememberNavState(navigator)
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            bottomBar = {
                BottomNavigationMenu(viewModel, navState.current) {
                    navState.goTo(it)
                }
            },
        ) { paddingValues ->
            CurrentScreen(Modifier
                    .padding(paddingValues)
                    .padding(15.dp)
                    .padding(bottom = 10.dp)
            )
        }
    }
}

@Composable
private fun BottomNavigationMenu(viewModel: VkViewModel,
                                 currentScreen: Screen,
                                 onNavItemClick: (Screen) -> Unit = {}) {
    BottomNavigation {
        BottomNavItems.values()
            .forEach {
                BottomNavigationItem(
                    selected = currentScreen::class == it.screenType,
                    onClick = { onNavItemClick(it.screen(viewModel)) },
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

@Composable
private fun CurrentScreen(modifier: Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    val currentScreen = navigator.lastItem as? ModifiableScreen
    currentScreen?.modifier = modifier
    CurrentScreen()
}
