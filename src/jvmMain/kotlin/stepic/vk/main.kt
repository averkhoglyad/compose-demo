package stepic.vk

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cafe.adriel.voyager.navigator.Navigator
import stepic.vk.representation.theme.VkAppTheme
import stepic.vk.navigation.screen.MainScreen
import java.awt.Dimension

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        window.minimumSize = Dimension(600, 400)
        VkAppTheme {
            Navigator(MainScreen)
        }
    }
}
