package stepic.vk.screen

import androidx.compose.runtime.Composable
import stepic.vk.representation.view.favorite.FavoritesView

object FavoritesScreen : ModifiableScreen() {

    override val key = "FavoritesScreen"

    @Composable
    override fun Content() {
        FavoritesView(modifier = this.modifier)
    }
}
