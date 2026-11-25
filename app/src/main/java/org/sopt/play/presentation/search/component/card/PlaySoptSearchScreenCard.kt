package org.sopt.play.presentation.search.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptSearchScreenCard(
    title: String,
    content: String,
    author: String,
    createdAt: String,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val descriptionColor = PlaySoptTheme.colors.gray3
    val descriptionStyle = PlaySoptTheme.typography.m12

    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = PlaySoptTheme.colors.gray1)
            .combinedClickable(
                onClick = {},
                onLongClick = { onLongClick() },
            )
            .padding(all = 20.dp),
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(space = 4.dp, Alignment.CenterHorizontally)) {
            Text(
                text = author,
                color = descriptionColor,
                style = descriptionStyle,
            )

            VerticalDivider(
                modifier = Modifier.height(12.dp),
                thickness = 1.dp,
                color = descriptionColor,
            )

            Text(
                text = createdAt,
                color = descriptionColor,
                style = descriptionStyle,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            color = PlaySoptTheme.colors.gray5,
            style = PlaySoptTheme.typography.m16,
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = content,
            color = PlaySoptTheme.colors.gray5,
            style = PlaySoptTheme.typography.m16,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaySoptSearchScreenCardPreview() {
    PlaySoptTheme {
        PlaySoptSearchScreenCard(
            title = "제목",
            content = "본문입니다.",
            author = "작성자",
            createdAt = "YYYY.MM.DD",
            onLongClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
