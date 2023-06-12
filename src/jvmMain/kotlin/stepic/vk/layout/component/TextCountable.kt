package stepic.vk.layout.component

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier

@Composable
fun TextCountable(name: String, modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    Text(
        text = "$name (Clicked $count)",
        modifier = modifier
            .clickable { count++ }
    )
}
