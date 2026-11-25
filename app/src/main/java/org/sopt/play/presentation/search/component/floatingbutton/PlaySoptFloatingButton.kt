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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

@Composable
fun PlaySoptFloatingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(PlaySoptTheme.colors.red),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_plus),
            contentDescription = null,
            modifier = modifier.noRippleClickable(onClick = onClick),
            tint = PlaySoptTheme.colors.white
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
