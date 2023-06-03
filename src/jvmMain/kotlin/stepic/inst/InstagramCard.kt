package stepic.inst

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun InstagramCardPreview() {
    InstProjectTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(10.dp)
        ) {
            InstagramCard()
        }
    }
}

@Composable
fun InstagramCard() {
    Card(
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = 10.dp
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colors.secondary)
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(5.dp)
                ) {
                    Img()
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                        .padding(5.dp)
                ) {
                    Metric(value = 6950, text = "Posts", modifier = Modifier.fillMaxWidth())
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F).padding(5.dp)
                ) {
                    Metric(value = 436, text = "Followers", modifier = Modifier.fillMaxWidth())
                }
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .weight(1F)
                        .padding(5.dp)
                ) {
                    Metric(value = 76, text = "Following", modifier = Modifier.fillMaxWidth())
                }
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text("Instagram")
                Text("#HashTag")
                Text("https://some.url")
                Button({}) {
                    Text("Follow")
                }
            }
        }
    }
}

@Composable
private fun Img(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.width(70.dp)
            .border(1.dp, MaterialTheme.colors.secondary)
            .padding(5.dp)
    ) {
        Box(
            modifier = modifier
                .background(Color.Magenta)
                .size(50.dp)
        )
    }
}

@Composable
private fun Metric(value: Int, text: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .border(1.dp, MaterialTheme.colors.secondary)
            .padding(5.dp)
    ) {
        Text(value.toString())
        Text(text)
    }
}
