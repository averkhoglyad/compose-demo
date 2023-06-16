package stepic.vk.model

import cafe.adriel.voyager.core.model.StateScreenModel
import stepic.vk.data.MetricItem
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import java.net.URI
import java.time.Instant
import kotlin.random.Random

class PostsViewModel(private val count: Int = 3): StateScreenModel<PostScreenState>(PostScreenState.Initial) {

    val screenState by state

    fun loadPosts() {
        val posts = generateSequence(1) { it + 1 }
            .take(count)
            .map { id -> generatePost(id) }
            .toList()

        mutableState.value = PostScreenState.Posts(posts)
    }

    private fun generatePost(id: Int) = VkPost(
            id = id,
            community = "/dev/null",
            avatar = URI("/vk/dev-null.jpg"),
            contentText = "ID: $id\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            contentImage = URI("/vk/post-img.jpg"),
            publishedAt = Instant.now(),
            metrics = listOf(
                MetricItem(MetricType.LIKES, Random.nextInt(10, 50)),
                MetricItem(MetricType.VIEWS, Random.nextInt(500, 1500)),
                MetricItem(MetricType.SHARES, Random.nextInt(50, 200)),
                MetricItem(MetricType.COMMENTS, Random.nextInt(20, 70)),
            )
        )

    fun incMetric(target: VkPost, metricType: MetricType) {
        val screenState = state.value
        if (screenState !is PostScreenState.Posts) {
            return
        }

        val modifiedPosts = screenState.posts
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

        mutableState.value = screenState.copy(posts = modifiedPosts)
    }

    fun drop(target: VkPost) {
        val screenState = state.value
        if (screenState !is PostScreenState.Posts) {
            return
        }

        val modifiedPosts = screenState.posts - target
        mutableState.value = screenState.copy(posts = modifiedPosts)
    }
}

sealed class PostScreenState {

    object Initial : PostScreenState()

    data class Posts(val posts: List<VkPost>) : PostScreenState()

}
