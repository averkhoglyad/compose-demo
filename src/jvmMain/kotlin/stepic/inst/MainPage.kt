package stepic.inst

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
fun MainPage(card: CardViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(10.dp)
    ) {
        LazyColumn {
            items(1000) {
                InstagramCard(card = card)
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

fun main() = application {
    val card = CardViewModel()
    Window(onCloseRequest = ::exitApplication) {
        InstProjectTheme {
            MainPage(card)
        }
    }
}
