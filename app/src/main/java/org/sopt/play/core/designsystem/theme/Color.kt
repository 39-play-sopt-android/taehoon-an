package org.sopt.play.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

// Primary
private val White = Color(0xFFFFFFFF)
private val Black = Color(0xFF191919)
private val Red = Color(0xFFFF4D4D)

// Grayscale
private val Gray1 = Color(0xFFF7F7F7)
private val Gray2 = Color(0xFFD1D5D6)
private val Gray3 = Color(0xFFB2BABD)
private val Gray5 = Color(0xFF505559)
private val Gray6 = Color(0xFF23272A)

@Immutable
data class PlaySoptColors(
    // Primary
    val white: Color,
    val black: Color,
    val red: Color,

    // Gray
    val gray1: Color,
    val gray2: Color,
    val gray3: Color,
    val gray5: Color,
    val gray6: Color,
)

val defaultPlaySoptColors = PlaySoptColors(
    white = White,
    black = Black,
    red = Red,

    gray1 = Gray1,
    gray2 = Gray2,
    gray3 = Gray3,
    gray5 = Gray5,
    gray6 = Gray6,

)
