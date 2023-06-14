package stepic.vk.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import stepic.vk.data.PostComment
import stepic.vk.data.VkPost
import stepic.vk.immutable

class CommentsModel {

    private val _postState = mutableStateOf<VkPost?>(null)
    val postState = _postState.immutable()
    var post: VkPost? by _postState

    private val _commentsState = mutableStateOf(emptyList<PostComment>())
    val commentsState = _commentsState.immutable()
    var comments: List<PostComment> by _commentsState

}