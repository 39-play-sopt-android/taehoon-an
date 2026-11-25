package org.sopt.play.presentation.search.component.floatingbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

@Composable
fun PlaySoptFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(60.dp)
            .dropShadow(
                shape = CircleShape,
                shadow = Shadow(
                    radius = 12.dp,
                    spread = 0.dp,
                    offset = DpOffset(x = 0.dp, y = 4.dp),
                    color = Color.Black.copy(alpha = 0.15f),
                ),
            )
            .clip(CircleShape)
            .background(PlaySoptTheme.colors.red)
            .noRippleClickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_plus),
            contentDescription = "글 작성",
            tint = PlaySoptTheme.colors.white,
        )
    }
}

@Preview
@Composable
private fun PlaySoptFloatingButton() {
    PlaySoptTheme {
        PlaySoptFloatingButton(onClick = {})
    }
}
