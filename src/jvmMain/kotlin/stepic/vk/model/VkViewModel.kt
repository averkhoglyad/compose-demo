package stepic.vk.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import stepic.vk.data.MetricItem
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import stepic.vk.data.VkPostComment
import stepic.vk.immutable
import stepic.vk.layout.component.getByType
import stepic.vk.layout.view.ScreenState
import java.net.URI
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.random.Random

class VkViewModel(count: Int = 3) {

    private val _feedScreenState = mutableStateOf<ScreenState>(ScreenState.None)
    val feedScreenState = _feedScreenState.immutable()
    val feedScreen: ScreenState by _feedScreenState

    private var prevFeedScreen: ScreenState = ScreenState.None

    init {
        var inc = 0
        val posts = generateSequence {
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

        _feedScreenState.value = ScreenState.PostsFeed(posts = posts)
    }

    fun showComments(post: VkPost) {
        var inc = 0
        val comments = generateSequence {
            VkPostComment(
                id = ++inc,
                author = "Somebody",
                authorAvatar = URI("/vk/author-avatar.png"),
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                publishedAt = Instant.now()
                    .minus(7, ChronoUnit.HOURS)
                    .plus(3L * inc, ChronoUnit.MINUTES)
            )
        }
            .take(post.metrics.getByType(MetricType.COMMENTS).value)
            .toList()
        prevFeedScreen = _feedScreenState.value
        _feedScreenState.value = ScreenState.CommentsList(post, comments)
    }

    fun closeComments() {
        _feedScreenState.value = prevFeedScreen
    }

    fun incMetric(target: VkPost, metricType: MetricType) {
        val currentFeedScreen = feedScreen
        if (currentFeedScreen !is ScreenState.PostsFeed) {
            return
        }

        val modifiedPosts = currentFeedScreen.posts
            .asSequence()
            .map { post ->
                if (post.id == target.id) {
                    val newMetrics = post.metrics
                        .asSequence()
                        .map { if (it.type == metricType) it.copy(value = it.value + 1) else it }
                        .toList()
                    post.copy(metrics = newMetrics)
                } else {
                    post
                }
            }
            .toList()

        _feedScreenState.value = currentFeedScreen.copy(posts = modifiedPosts)
    }

    fun drop(target: VkPost) {
        val currentFeedScreen = feedScreen
        if (currentFeedScreen !is ScreenState.PostsFeed) {
            return
        }

        val modifiedPosts = currentFeedScreen.posts - target
        _feedScreenState.value = currentFeedScreen.copy(posts = modifiedPosts)
    }
}