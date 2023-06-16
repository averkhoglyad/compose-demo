package stepic.vk.model

import kotlinx.coroutines.flow.StateFlow
import kotlin.reflect.KProperty

@Suppress("NOTHING_TO_INLINE")
inline operator fun <T> StateFlow<T>.getValue(thisObj: Any?, property: KProperty<*>): T = this.value
