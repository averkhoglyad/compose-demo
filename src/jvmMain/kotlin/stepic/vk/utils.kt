package stepic.vk

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State

fun <T> MutableState<T>.immutable() = this as State<T>
