package org.sopt.play.core.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = PlaySoptTheme.colors.black,
            contentColor = PlaySoptTheme.colors.gray1,
            disabledContainerColor = PlaySoptTheme.colors.gray1,
            disabledContentColor = PlaySoptTheme.colors.gray3,
        ),
    ) {
        Text(
            text = text,
            style = PlaySoptTheme.typography.sb14
        )
    }
}
