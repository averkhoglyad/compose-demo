package stepic.inst

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun InstProjectTheme(darkTheme: Boolean = isSystemInDarkTheme(),
                     content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkPalette else LightPalette
    MaterialTheme(colors = colors, content = content)
}

private val DarkPalette = darkColors()

private val LightPalette = lightColors()
