package org.sopt.play.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

@Composable
fun PlaySoptButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val backgroundColor = if (enabled) {
        PlaySoptTheme.colors.black
    } else {
        PlaySoptTheme.colors.gray1
    }

    val textColor = if (enabled) {
        PlaySoptTheme.colors.gray1
    } else {
        PlaySoptTheme.colors.gray3
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(
                enabled = enabled,
                onClick = onClick
            )
            .clip(shape = CircleShape)
            .background(color = backgroundColor)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = PlaySoptTheme.typography.sb14
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
                .padding(16.dp),
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
        }
    }
}
