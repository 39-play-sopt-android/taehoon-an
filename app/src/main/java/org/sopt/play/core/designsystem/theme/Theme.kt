package org.sopt.play.core.designsystem.theme

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val LocalPlaySoptColorProvider = staticCompositionLocalOf {
    defaultPlaySoptColors
}

val LocalPlaySoptTypographyProvider = staticCompositionLocalOf {
    defaultPlaySoptTypography
}

@Composable
fun PlaySoptTheme(
    colors: PlaySoptColors = defaultPlaySoptColors,
    typography: PlaySoptTypography = defaultPlaySoptTypography,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val activity = LocalActivity.current
    if (!view.isInEditMode) {
        SideEffect {
            activity?.window?.let { window ->
                WindowCompat.getInsetsController(window, view)
                    .isAppearanceLightStatusBars = true
            }
        }
    }

    CompositionLocalProvider(
        LocalPlaySoptColorProvider provides colors,
        LocalPlaySoptTypographyProvider provides typography
    ) {
        MaterialTheme(
            content = content
        )
    }
}

object PlaySoptTheme {
    val colors: PlaySoptColors
        @Composable
        @ReadOnlyComposable
        get() = LocalPlaySoptColorProvider.current
    val typography: PlaySoptTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalPlaySoptTypographyProvider.current
}
