package stepic.vk

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import stepic.vk.model.PostsFeedViewModel
import stepic.vk.layout.MainLayout
import stepic.vk.layout.VkProjectTheme

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        VkProjectTheme {
            MainLayout()
        }
    }
}
