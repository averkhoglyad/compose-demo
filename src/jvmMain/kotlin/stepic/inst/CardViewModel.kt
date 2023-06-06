package stepic.inst

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class CardViewModel {

    private val _isFollowingState = mutableStateOf(false)

    var isFollowing by _isFollowingState
        private set

    var isFollowingState: State<Boolean> = _isFollowingState

    fun toggleFollowingState() {
        isFollowing = !isFollowing
    }

}