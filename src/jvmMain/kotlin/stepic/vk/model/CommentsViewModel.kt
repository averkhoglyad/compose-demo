package stepic.vk.model

import cafe.adriel.voyager.core.model.StateScreenModel
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import stepic.vk.data.VkPostComment
import stepic.vk.layout.component.getByType
import java.net.URI
import java.time.Instant
import java.time.temporal.ChronoUnit

class CommentsViewModel: StateScreenModel<CommentsScreenState>(CommentsScreenState.Initial) {

    val screenState: CommentsScreenState by state

    fun loadComments(post: VkPost) {
        val comments = generateSequence(1) { it + 1 }
            .take(post.metrics.getByType(MetricType.COMMENTS).value)
            .map{ id -> generateComment(id)}
            .toList()

        mutableState.value = CommentsScreenState.Comments(post, comments)
    }

    private fun generateComment(id: Int) = VkPostComment(
        id = id,
        author = "Somebody",
        authorAvatar = URI("/vk/author-avatar.png"),
        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
        publishedAt = Instant.now()
            .minus(7, ChronoUnit.HOURS)
            .plus(3L * id, ChronoUnit.MINUTES)
    )
}

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(val post: VkPost,
                        val comments: List<VkPostComment>): CommentsScreenState()

}
