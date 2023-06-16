package stepic.vk

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import stepic.vk.layout.MainLayout
import stepic.vk.layout.VkAppTheme

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        VkAppTheme {
            MainLayout()
        }
    }
}
