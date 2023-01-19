package com.example.todolistcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette =
    darkColors(primary = BrightOrange, primaryVariant = MediumOrange, secondary = DarkOrange)

private val LightColorPalette =
    lightColors(primary = BrightOrange, primaryVariant = MediumOrange, secondary = DarkOrange)

@Composable
fun TodoListComposeTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(colors = colors, typography = Typography, shapes = Shapes, content = content)
}