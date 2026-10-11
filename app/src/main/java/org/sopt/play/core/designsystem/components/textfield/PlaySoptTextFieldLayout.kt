package org.sopt.play.core.designsystem.components.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptTextFieldLayout(
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    errorLabel: String? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            modifier = Modifier.padding(start = 6.dp, bottom = 6.dp),
            color = PlaySoptTheme.colors.gray6,
            style = PlaySoptTheme.typography.sb16,
        )

        content()

        AnimatedVisibility(
            visible = isError && errorLabel != null,
            enter = fadeIn(tween(200)) + expandVertically(
                animationSpec = tween(200),
                expandFrom = Alignment.Top,
            ),
            exit = fadeOut(tween(200)) + shrinkVertically(
                animationSpec = tween(200),
                shrinkTowards = Alignment.Top,
            ),
        ) {
            Text(
                text = errorLabel.orEmpty(),
                modifier = Modifier.padding(start = 6.dp, top = 6.dp),
                color = PlaySoptTheme.colors.red,
                style = PlaySoptTheme.typography.m14,
            )
        }
    }
}
