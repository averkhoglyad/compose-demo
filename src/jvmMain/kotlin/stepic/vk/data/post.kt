package stepic.vk.data

import java.net.URI
import java.time.Instant

data class VkPost(val community: String,
                  val avatar: URI,
                  val publishedAt: Instant,
                  val contentText: String,
                  val contentImage: URI,
                  val metrics: Collection<MetricItem> = emptyList())

data class MetricItem(val type: MetricType,
                      val value: Int)

enum class MetricType {
    VIEWS, COMMENTS, SHARES, LIKES
}
