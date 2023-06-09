package stepic.inst

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import kotlin.random.Random

class InstViewModel {

    private val _cardsState = mutableStateOf(emptyList<InstCard>())
    val cardsState: State<List<InstCard>> = _cardsState
    val cards: List<InstCard> by _cardsState

    init {
        _cardsState.value = mutableListOf<InstCard>()
            .apply {
                repeat(500) { add(InstCard(it, "Title: $it", Random.nextBoolean())) }
            }
    }

    fun toggleFollowed(card: InstCard) {
        val list = _cardsState.value.toMutableList()
        list.replaceAll {
                if (it == card) {
                    card.copy(isFollowed = !card.isFollowed)
                } else {
                    it
                }
            }
        _cardsState.value = list
    }

}