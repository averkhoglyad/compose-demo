package stepic.inst

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CardViewModel {

    private val _isFollowingState = mutableStateOf(false)
    val isFollowingState: State<Boolean> = _isFollowingState
    var isFollowing by _isFollowingState
        private set

    fun toggleFollowingState() {
        isFollowing = !isFollowing
    }

}