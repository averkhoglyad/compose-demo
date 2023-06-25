package stepic.vk.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow


@Composable
fun rememberNavState(navigator: Navigator = LocalNavigator.currentOrThrow) = remember { NavState(navigator) }

class NavState(private val navigator: Navigator) {

    val current: Screen
        get() = navigator.lastItem

    fun goToTab(screen: Screen) {
        navigator.popUntilRoot()
        if (navigator.lastItem.key == screen.key) {
            return
        }
        navigator.push(screen)
    }

    fun goTo(screen: Screen) {
        if (navigator.lastItem.key == screen.key) {
            return
        }
        navigator.push(screen)
    }

    fun goBack() {
        navigator.pop()
    }
}
