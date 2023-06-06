package stepic.vk.model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import stepic.vk.data.MetricItem
import stepic.vk.data.VkPost

class FeedViewModel(post: VkPost) {

    private val _postState = mutableStateOf(post)
    val postState: State<VkPost> = _postState
    var post by _postState
        private set

    fun incMetric(metric: MetricItem) {
        update {
            val newMetrics = it.metrics.filter { el -> el.type != metric.type } + metric.copy(value = metric.value + 1)
            it.copy(metrics = newMetrics)
        }
    }

    fun update(fn: (VkPost) -> VkPost) {
        post = fn(post)
    }

}