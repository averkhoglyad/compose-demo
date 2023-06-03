package stepic.vk

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun PostCardPreview() {
    VkProjectTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(10.dp)) {
            PostCard()
        }
    }
}

@Composable
fun PostCard() {
    Card {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Caption(modifier = Modifier.padding(bottom = 8.dp))
            Content()
            Bottom()
        }
    }
}

@Composable
private fun Caption(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource("/vk/dev-null.jpg"),
            contentDescription = "Avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(70.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(
            modifier = Modifier
                .weight(1F)
                .padding(start = 5.dp, end = 5.dp)
        ) {
            Text(
                text = "/dev/null",
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "14:00",
                color = MaterialTheme.colors.onSecondary
            )
        }
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = "",
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
private fun Content() {
    Text(
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        textAlign = TextAlign.Justify
    )
    Image(
        painter = ColorPainter(Color.Magenta),
        contentDescription = "",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.size(500.dp)
    )
}

@Composable
private fun Bottom() {
    Row {
        PostViews()
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        )
        Reposts()
        Comments()
        Likes()
    }
}

@Composable
private fun PostViews() {
    Text("206")
    Text("(.)")
}

@Composable
private fun Reposts() {
    Text("206")
    Text("->")
}

@Composable
private fun Comments() {
    Text("11")
    Text("[]")
}

@Composable
private fun Likes() {
    Text("491")
    Text("<3")
}
