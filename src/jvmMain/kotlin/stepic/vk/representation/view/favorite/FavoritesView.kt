package stepic.vk.representation.view.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepic.vk.representation.component.TextCountable

@Composable
fun FavoritesView(modifier: Modifier = Modifier) {
    TextCountable("FAVORITE", modifier = modifier)
}
