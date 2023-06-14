package stepic.vk

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import stepic.vk.model.VkViewModel
import stepic.vk.layout.MainLayout
import stepic.vk.layout.VkProjectTheme
import stepic.vk.layout.view.CommentsView
import stepic.vk.model.CommentsModel

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        VkProjectTheme {
            MainLayout(VkViewModel(7))
        }
    }
}
