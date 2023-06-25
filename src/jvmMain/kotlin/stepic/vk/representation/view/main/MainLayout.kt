package stepic.vk.representation.view.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import stepic.vk.navigation.screen.CurrentScreen
import stepic.vk.navigation.rememberNavState
import stepic.vk.navigation.screen.HomeScreen

@Composable
fun MainLayout() {
    Navigator(
        screen = HomeScreen,
        disposeBehavior = NavigatorDisposeBehavior(false, false)
    ) { navigator ->
        val navState = rememberNavState(navigator)
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            bottomBar = {
                BottomNavigationMenu(
                    currentScreen = navState.current,
                    onNavItemClick = { navState.goToTab(it) }
                )
            },
        ) { paddingValues ->
            CurrentScreen(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(15.dp)
                    .padding(bottom = 10.dp)
            )
        }
    }
}

@Composable
private fun BottomNavigationMenu(
    currentScreen: Screen,
    onNavItemClick: (Screen) -> Unit = {}
) {
    BottomNavigation {
        BottomNavItems.values()
            .forEach {
                val selected = currentScreen::class == it.screenType
                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            onNavItemClick(it.screen())
                        }
                    },
                    icon = {
                        Icon(imageVector = it.icon, contentDescription = it.title)
                    },
                    label = {
                        Text(text = it.title)
                    },
                    selectedContentColor = MaterialTheme.colors.onPrimary,
                    unselectedContentColor = MaterialTheme.colors.onSecondary,
                )
            }
    }
}
