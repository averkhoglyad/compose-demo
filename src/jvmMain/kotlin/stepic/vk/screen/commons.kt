package stepic.vk.screen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.*

abstract class ModifiableScreen : Screen {

    var modifier: Modifier = Modifier

}

@Composable
fun CurrentScreen(modifier: Modifier) {
    val navigator = LocalNavigator.currentOrThrow
    val currentScreen = navigator.lastItem as? ModifiableScreen
    currentScreen?.modifier = modifier
    CurrentScreen()
}
