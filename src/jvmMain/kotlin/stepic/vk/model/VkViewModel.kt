package stepic.vk.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import stepic.vk.data.MetricItem
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import stepic.vk.immutable
import stepic.vk.navigation.BottomNavItems
import stepic.vk.navigation.NavItem
import java.net.URI
import java.time.Instant
import kotlin.random.Random

class VkViewModel(count: Int = 3) {

    private val _postsState = mutableStateOf(emptyList<VkPost>())
    val postsState = _postsState.immutable()
    val posts: List<VkPost> by _postsState

    init {
        var inc = 0
        _postsState.value = generateSequence {
            VkPost(
                id = ++inc,
                community = "/dev/null",
                avatar = URI("/vk/dev-null.jpg"),
                contentText = "ID: $inc\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                contentImage = URI("/vk/post-img.jpg"),
                publishedAt = Instant.now(),
                metrics = listOf(
                    MetricItem(MetricType.LIKES, Random.nextInt(10, 50)),
                    MetricItem(MetricType.VIEWS, Random.nextInt(500, 1500)),
                    MetricItem(MetricType.SHARES, Random.nextInt(50, 200)),
                    MetricItem(MetricType.COMMENTS, Random.nextInt(20, 70)),
                )
            )
        }
            .take(count)
            .toList()
    }

    fun incMetric(target: VkPost, metricType: MetricType) {
        _postsState.value = _postsState.value.asSequence()
            .map { post ->
                if (post.id == target.id) {
                    val newMetrics = post.metrics.asSequence()
                        .map { if (it.type == metricType) it.copy(value = it.value + 1) else it }
                        .toList()
                    post.copy(metrics = newMetrics)
                } else {
                    post
                }
            }
            .toList()
    }

    fun drop(post: VkPost) {
        _postsState.value -= post
    }
}