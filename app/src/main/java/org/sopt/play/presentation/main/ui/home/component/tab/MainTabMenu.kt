package org.sopt.play.presentation.main.ui.home.component.tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

@Composable
fun MainTabMenu(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textStyle = PlaySoptTheme.typography.m18
    val textColor = if (isSelected) PlaySoptTheme.colors.black else PlaySoptTheme.colors.gray3

    val indicatorColor = PlaySoptTheme.colors.black

    Column(
        modifier = modifier
            .noRippleClickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .padding(2.dp)
                .background(
                    color = if (isSelected) indicatorColor else Color.Transparent,
                    shape = CircleShape,
                ),
        )

        Text(
            text = text,
            style = textStyle,
            color = textColor,
            modifier = Modifier.padding(bottom = 8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainTabMenuPreview() {
    PlaySoptTheme {
        Row() {
            MainTabMenu(
                text = "메뉴",
                isSelected = true,
                onClick = {},
            )

            MainTabMenu(
                text = "메뉴",
                isSelected = false,
                onClick = {},
            )
        }
    }
}
