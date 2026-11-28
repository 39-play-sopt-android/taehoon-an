package org.sopt.play.core.designsystem.components.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

enum class PlaySoptButtonType {
    DEFAULT,
    CANCEL;

    val backgroundColor: Color
        @Composable
        get() = when (this) {
            DEFAULT -> PlaySoptTheme.colors.black
            CANCEL -> PlaySoptTheme.colors.gray1
        }

    val textColor: Color
        @Composable
        get() = when (this) {
            DEFAULT -> PlaySoptTheme.colors.gray1
            CANCEL -> PlaySoptTheme.colors.gray5
        }
}

@Composable
fun PlaySoptButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    type: PlaySoptButtonType = PlaySoptButtonType.DEFAULT,
    enabled: Boolean = true,
) {
    val backgroundColor = if (enabled) type.backgroundColor else PlaySoptTheme.colors.gray1
    val textColor = if (enabled) type.textColor else PlaySoptTheme.colors.gray3

    Box(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(
                enabled = enabled,
                onClick = onClick,
            )
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = textColor,
            style = PlaySoptTheme.typography.sb14,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaySoptButtonPreview() {
    PlaySoptTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PlaySoptButton(
                text = "로그인",
                onClick = {},
                enabled = false,
            )

            PlaySoptButton(
                text = "로그인",
                onClick = {},
                enabled = true,
            )

            PlaySoptButton(
                text = "로그인",
                onClick = {},
                type = PlaySoptButtonType.CANCEL,
                enabled = true,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaySoptButtonWidthPreview() {
    PlaySoptTheme {
        PlaySoptButton(
            text = "로그인 만약에 글씨가 길어짐",
            onClick = {},
            modifier = Modifier.width(141.dp),
            enabled = true,
        )
    }
}

