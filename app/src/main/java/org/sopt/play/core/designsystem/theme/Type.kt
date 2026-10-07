package org.sopt.play.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.sopt.play.R

@Immutable
data class PlaySoptTypography(
    // b
    val b28: TextStyle,

    // m
    val m14: TextStyle,
    val m18: TextStyle,

    // sb
    val sb14: TextStyle,
    val sb16: TextStyle
)

private val pretendardFontFamily = FontFamily(
    Font(R.font.pretendard_medium, weight = FontWeight.Medium),
    Font(R.font.pretendard_semibold, weight = FontWeight.SemiBold),
    Font(R.font.pretendard_bold, weight = FontWeight.Bold),
)

private object TypographyDefaults {
    // b
    val BLetterSpacing = (-0.01).em
    val BLineHeight = 1.2.em

    // m
    val MLetterSpacing = (-0.01).em
    val MLineHeight = 1.2.em

    // sb
    val SBLetterSpacing = (-0.01).em
    val SBLineHeight = 1.2.em
}

val defaultPlaySoptTypography = PlaySoptTypography(

    //b
    b28 = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = TypographyDefaults.BLineHeight,
        letterSpacing = TypographyDefaults.BLetterSpacing
    ),

    //m
    m14 = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = TypographyDefaults.MLineHeight,
        letterSpacing = TypographyDefaults.MLetterSpacing
    ),

    m18 = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = TypographyDefaults.MLineHeight,
        letterSpacing = TypographyDefaults.MLetterSpacing
    ),

    //sb
    sb14 = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = TypographyDefaults.SBLineHeight,
        letterSpacing = TypographyDefaults.SBLetterSpacing
    ),

    sb16 = TextStyle(
        fontFamily = pretendardFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = TypographyDefaults.SBLineHeight,
        letterSpacing = TypographyDefaults.SBLetterSpacing
    ),
)
