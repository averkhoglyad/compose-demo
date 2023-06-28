package stepic.vk

import androidx.compose.ui.window.Window
import cafe.adriel.voyager.navigator.Navigator
import stepic.vk.config.initKoin
import stepic.vk.representation.theme.VkAppTheme
import stepic.vk.screen.MainScreen
import stepic.vk.util.koinApp
import java.awt.Dimension

fun main() = koinApp(
    startKoin = { initKoin() }
) {
    Window(
        onCloseRequest = ::exitApplication
    ) {
        VkAppTheme {
            window.minimumSize = Dimension(600, 400)
            Navigator(MainScreen)
        }
    }
}
