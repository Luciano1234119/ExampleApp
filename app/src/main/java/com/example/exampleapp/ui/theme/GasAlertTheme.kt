package com.example.exampleapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val GasAlertColorScheme = lightColorScheme(
    primary = Color(0xFF4A5CF5),
    secondary = Color(0xFF6C63FF),
    background = Color(0xFFF8F8FB),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun GasAlertTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GasAlertColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}
