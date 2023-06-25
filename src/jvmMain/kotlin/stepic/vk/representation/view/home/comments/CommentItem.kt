package stepic.vk.representation.view.home.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import stepic.vk.data.VkPostComment
import stepic.vk.representation.theme.TIME_FORMATTER
import java.time.ZoneId

@Composable
fun CommentItem(comment: VkPostComment,
                modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Image(
            painter = painterResource(comment.authorAvatar.toString()),
            contentDescription = "Author Avatar",
            modifier = Modifier
                .clip(CircleShape)
                .size(32.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.weight(1F),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = comment.author,
                color = MaterialTheme.colors.onPrimary,
                fontSize = 12.sp
            )
            Text(
                text = "ID: ${comment.id}\n${comment.text}",
                color = MaterialTheme.colors.onPrimary ,
                fontSize = 14.sp
            )
            Text(
                text = comment.publishedAt.atZone(ZoneId.systemDefault()).format(TIME_FORMATTER),
                color = MaterialTheme.colors.onSecondary,
                fontSize = 12.sp
            )
        }
    }
}
