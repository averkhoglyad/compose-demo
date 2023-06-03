package stepic.multitable

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun MultiplyTable() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            for (j in 1..9) {
                Row(modifier = Modifier.weight(1F)) {
                    for (i in 1..9) {
                        val color = if ((i + j) % 2 == 0) Color.Yellow else Color.Unspecified
                        Box(contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxSize().weight(1F)
                                .border(width = 1.dp, color = Color.DarkGray)
                                .background(color)) {
                            Text((i * j).toString())
                        }
                    }
                }
            }
        }
    }
}
