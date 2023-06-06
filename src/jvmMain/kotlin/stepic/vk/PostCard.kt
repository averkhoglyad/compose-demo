package stepic.vk

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.sp
import stepic.vk.data.MetricItem
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private val publishTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

@Composable
fun PostCard(post: VkPost,
             modifier: Modifier = Modifier,
             onViewsClick: (MetricItem) -> Unit = {},
             onLikeClick: (MetricItem) -> Unit = {},
             onSharesClick: (MetricItem) -> Unit = {},
             onCommentsClick: (MetricItem) -> Unit = {}) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Header(
                post = post,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = post.contentText,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Image(
                painter = painterResource(post.contentImage.toString()),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.FillWidth
            )

            Footer(
                metrics = post.metrics,
                modifier = Modifier.padding(top = 15.dp),
                onItemClick = {
                    when (it.type) {
                        MetricType.VIEWS -> onViewsClick(it)
                        MetricType.COMMENTS -> onCommentsClick(it)
                        MetricType.SHARES -> onSharesClick(it)
                        MetricType.LIKES -> onLikeClick(it)
                    }
                }
            )
        }
    }
}

@Composable
private fun Header(post: VkPost,
                   modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(post.avatar.toString()),
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
                text = post.community,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = post.publishedAt.atZone(ZoneId.systemDefault()).format(publishTimeFormatter),
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
private fun Footer(metrics: Collection<MetricItem>,
                   modifier: Modifier = Modifier,
                   onItemClick: (MetricItem) -> Unit = {}) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Metric(metric = metrics.getByType(MetricType.VIEWS), icon = Icons.Rounded.Search, onMetricClick = onItemClick)
        Spacer(modifier = Modifier.weight(1F))
        Metric(metric = metrics.getByType(MetricType.SHARES), icon = Icons.Rounded.Share, onMetricClick = onItemClick)
        Spacer(modifier = Modifier.size(10.dp))
        Metric(metric = metrics.getByType(MetricType.COMMENTS), icon = Icons.Rounded.Email, onMetricClick = onItemClick)
        Spacer(modifier = Modifier.size(10.dp))
        Metric(metric = metrics.getByType(MetricType.LIKES), icon = Icons.Rounded.Favorite, onMetricClick = onItemClick)
    }
}

private fun Collection<MetricItem>.getByType(type: MetricType): MetricItem {
    return this.find { it.type == type } ?: MetricItem(type, 0)
}

@Composable
private fun Metric(metric: MetricItem,
                   icon: ImageVector,
                   modifier: Modifier = Modifier,
                   onMetricClick: (MetricItem) -> Unit = {}) {
    Metric(value = metric.value, icon = icon, modifier = modifier) { onMetricClick(metric) }
}

@Composable
private fun Metric(value: Int,
                   icon: ImageVector,
                   description: String = "",
                   modifier: Modifier = Modifier,
                   onItemClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { onItemClick() }
    ) {
        Text(
            text = value.toString(),
            color = MaterialTheme.colors.onSecondary,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.size(2.dp))
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}
