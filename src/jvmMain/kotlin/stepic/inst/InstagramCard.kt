package stepic.inst

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
fun InstagramCard(card: InstCard,
                  onFollowToggle: (InstCard) -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp),
        border = BorderStroke(1.dp, Color.Gray),
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            CardHeader()
            Text(
                text = "Instagram ${card.id}",
                fontSize = 32.sp,
                fontFamily = FontFamily.Cursive
            )
            Text(
                text = "#${card.title}",
                fontSize = 14.sp
            )
            Text(
                text = "https://some.url",
                fontSize = 14.sp
            )
            FollowButton(card.isFollowed) { onFollowToggle(card) }
        }
    }
}

@Composable
private fun FollowButton(isFollowed: Boolean,
                         onFollowToggle: () -> Unit) {
    Button(
        onClick = {
            onFollowToggle()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isFollowed) MaterialTheme.colors.secondary else MaterialTheme.colors.primary
        )
    ) {
        if (isFollowed) {
            Text(text = "Unfollow")
        } else {
            Text("Follow")
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

        val metricsModifier = Modifier
            .weight(1F)
            .padding(5.dp)

        UserMetric(
            value = 6950,
            title = "Posts",
            modifier = metricsModifier
        )
        UserMetric(
            value = 4361234,
            title = "Followers",
            modifier = metricsModifier
        )
        UserMetric(
            value = 76,
            title = "Following",
            modifier = metricsModifier
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
