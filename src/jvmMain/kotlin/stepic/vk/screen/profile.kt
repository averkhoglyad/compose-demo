package stepic.vk.screen

import androidx.compose.runtime.Composable
import stepic.vk.representation.view.profile.ProfileView

object ProfileScreen : ModifiableScreen() {

    override val key = "ProfileScreen"

    @Composable
    override fun Content() {
        ProfileView(modifier = this.modifier)
    }
}