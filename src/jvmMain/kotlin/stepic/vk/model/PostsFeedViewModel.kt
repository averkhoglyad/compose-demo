package stepic.vk.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import stepic.vk.data.MetricItem
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import stepic.vk.immutable
import java.net.URI
import java.time.Instant
import kotlin.random.Random

class PostsFeedViewModel(count: Int = 3): ScreenModel {

    private val _screenStateState = mutableStateOf<PostScreenState>(PostScreenState.Initial)
    val screenStateState = _screenStateState.immutable()
    val screenState: PostScreenState by _screenStateState

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

        _screenStateState.value = PostScreenState.Posts(posts)
    }

    fun incMetric(target: VkPost, metricType: MetricType) {
        val currentFeedScreen = screenState
        if (currentFeedScreen !is PostScreenState.Posts) {
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

        _screenStateState.value = currentFeedScreen.copy(posts = modifiedPosts)
    }

    fun drop(target: VkPost) {
        val currentScreenState = screenState
        if (currentScreenState !is PostScreenState.Posts) {
            return
        }

        val modifiedPosts = currentScreenState.posts - target
        _screenStateState.value = currentScreenState.copy(posts = modifiedPosts)
    }
}

sealed class PostScreenState {

    object Initial : PostScreenState()

    data class Posts(val posts: List<VkPost>) : PostScreenState()

}
