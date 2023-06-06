package stepic.vk

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch

@Composable
fun MainPage() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var fabIsVisible by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = { bottomNavigation() },
        floatingActionButton = {
            if (fabIsVisible) {
                FloatingActionButton(onClick = {
                    scope.launch {
                        val snackbarRes = snackbarHostState
                            .showSnackbar(
                                message = "Lorem ipsum",
                                actionLabel = "Hide",
                                duration = SnackbarDuration.Long
                            )
                        if (snackbarRes == SnackbarResult.ActionPerformed) {
                            fabIsVisible = false
                        }
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        Text("Test")
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
            MainPage()
        }
    }
}
