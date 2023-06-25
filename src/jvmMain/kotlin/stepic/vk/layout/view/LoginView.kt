package stepic.vk.layout.view

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import stepic.vk.layout.component.TextLink

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onOauth: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource("vk/vk-logo.png"),
            contentDescription = "VK.com logo",
            modifier = Modifier.size(120.dp)
        )

        TextLink(
            text = "Open login page"
        ) {
            onLoginClick()
        }

        var value by remember { mutableStateOf("") }
        TextField(
            value = value,
            onValueChange = { value = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.0F)
        )

        Button(
            enabled = value.isNotEmpty(),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onOauth(value)
            }
        ) {
            Text("Apply Callback URL")
        }
    }
}