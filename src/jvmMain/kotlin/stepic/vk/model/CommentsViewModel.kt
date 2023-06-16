package stepic.vk.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import cafe.adriel.voyager.core.model.ScreenModel
import stepic.vk.data.MetricType
import stepic.vk.data.VkPost
import stepic.vk.data.VkPostComment
import stepic.vk.immutable
import stepic.vk.layout.component.getByType
import java.net.URI
import java.time.Instant
import java.time.temporal.ChronoUnit

class CommentsViewModel: ScreenModel {

    private val _screenStateState = mutableStateOf<CommentsScreenState>(CommentsScreenState.Initial)
    val screenStateState = _screenStateState.immutable()
    val screenState: CommentsScreenState by _screenStateState

    fun loadComments(post: VkPost) {
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
        _screenStateState.value = CommentsScreenState.Comments(post, comments)
    }
}

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(val post: VkPost,
                        val comments: List<VkPostComment>): CommentsScreenState()

}
