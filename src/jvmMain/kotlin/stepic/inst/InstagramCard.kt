package stepic.inst

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
        Column(modifier = Modifier.padding(16.dp)) {
            CardHeader()
            Text(
                text = "Instagram",
                fontSize = 32.sp,
                fontFamily = FontFamily.Cursive
            )
            Text(
                text = "#HashTag",
                fontSize = 14.sp
            )
            Text(
                text = "https://some.url",
                fontSize = 14.sp
            )
            Button(::noop) {
                Text("Follow")
            }
        }
    }
}

@Composable
private fun CardHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Logo()

        UserMetric(
            value = 6950,
            title = "Posts",
            modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        )
        UserMetric(
            value = 4361234,
            title = "Followers",
            modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        )
        UserMetric(
            value = 76,
            title = "Following",
            modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        )
    }
}

@Composable
private fun Logo(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource("inst/instagram-icon.png"),
        contentDescription = "Instagram Logo",
        modifier = modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.White)
            .padding(3.dp)
    )
}

@Composable
private fun UserMetric(value: Int, title: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = stringify(value),
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Cursive
        )
        Text(
            text = title,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun stringify(value: Int): String = when {
    (value < 1_000) -> "%d".format(value)
    (value < 1_000_000) -> "%dK".format(value / 1_000)
    (value < 1_000_000_000) -> "%dM".format(value / 1_000_000)
    else -> "%dG".format(value / 1_000_000_000)
}

private fun noop() {}