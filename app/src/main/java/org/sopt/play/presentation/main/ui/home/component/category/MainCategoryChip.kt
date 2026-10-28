package org.sopt.play.presentation.main.ui.home.component.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

@Composable
fun MainCategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val textStyle = PlaySoptTheme.typography.m14

    val textColor = if (isSelected) PlaySoptTheme.colors.white else PlaySoptTheme.colors.gray5
    val backgroundColor = if (isSelected) PlaySoptTheme.colors.gray5 else PlaySoptTheme.colors.white
    val borderColor = if (isSelected) PlaySoptTheme.colors.gray5 else PlaySoptTheme.colors.gray2

    val shape = RoundedCornerShape(6.dp)

    Text(
        text = text,
        modifier = modifier
            .clip(shape)
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = shape,
            )
            .noRippleClickable { onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        color = textColor,
        style = textStyle,
    )
}

@Preview(showBackground = true)
@Composable
private fun MainCategoryChipPreview() {
    PlaySoptTheme {
        Column(
            modifier = Modifier
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MainCategoryChip(
                    text = "칩",
                    isSelected = false,
                    onClick = {},
                )

                MainCategoryChip(
                    text = "칩",
                    isSelected = true,
                    onClick = {},
                )
            }
        }
    }
}
