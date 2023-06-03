package stepic.vk

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun PostCardPreview() {
    VkProjectTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .padding(10.dp)
        ) {
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
            Header(modifier = Modifier.padding(bottom = 8.dp))

            Text(
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Image(
                painter = painterResource("/vk/post-img.jpg"),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Footer()
        }
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
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

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = "",
                tint = MaterialTheme.colors.onSecondary
            )
        }
    }
}

@Composable
private fun Footer(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Metric(206, Icons.Rounded.Search)
        Spacer(
            modifier = Modifier
                .weight(1F)
        )
        Metric(11, Icons.Rounded.Share)
        Spacer(modifier = Modifier.size(10.dp))
        Metric(7, Icons.Rounded.Email)
        Spacer(modifier = Modifier.size(10.dp))
        Metric(27, Icons.Rounded.Favorite)
    }
}

@Composable
private fun Metric(value: Int, icon: ImageVector, description: String = "") {
    Text(
        text = value.toString(),
        color = MaterialTheme.colors.onSecondary
    )
    Spacer(modifier = Modifier.size(2.dp))
    Icon(
        imageVector = icon,
        contentDescription = description,
        tint = MaterialTheme.colors.onSecondary
    )
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        PostCardPreview()
    }
}
