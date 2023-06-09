package stepic.vk.layout.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import stepic.vk.layout.component.TextCountable

@Composable
fun FavoritesView(modifier: Modifier = Modifier) {
    TextCountable("FAVORITE", modifier = modifier)
}
