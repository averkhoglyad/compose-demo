package stepic.vk.data

import java.net.URI
import java.time.Instant

data class VkPostComment(val id: Int,
                         val author: String,
                         val authorAvatar: URI,
                         val text: String,
                         val publishedAt: Instant)