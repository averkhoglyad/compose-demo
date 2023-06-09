package stepic.inst

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainPage(model: InstViewModel) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(10.dp),
        floatingActionButton = {
            if (lazyListState.firstVisibleItemIndex > 3) {
                ScrollToTopFAB(onClick = {
                    scope.launch {
                        lazyListState.scrollToItem(3)
                        lazyListState.animateScrollToItem(0)
                    }
                })
            }
        }
    ) {
        LazyColumn(
            state = lazyListState
        ) {
            items(items = model.cards, key = InstCard::id) { card ->
                val dismissState = rememberDismissState()
                if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                    model.remove(card)
                }

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.StartToEnd),
                    background = {
                        Box(
                            contentAlignment = Alignment.CenterStart,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 50.dp)
                                .background(MaterialTheme.colors.error)
                                .padding(start = 5.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Remove Card",
                                tint = MaterialTheme.colors.onError
                            )
                        }
                    }
                ) {
                    InstagramCard(
                        card = card,
                        onFollowToggle = {
                            model.toggleFollowed(it)
                        }
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
            }
        }
    }
}

@Composable
private fun ScrollToTopFAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Scroll top",
            modifier = Modifier.rotate(90F)
        )
    }
}

fun main() = application {
    val card = InstViewModel()
    Window(onCloseRequest = ::exitApplication) {
        InstProjectTheme {
            MainPage(card)
        }
    }
}
