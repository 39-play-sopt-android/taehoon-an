package org.sopt.play.core.designsystem.components.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.components.button.PlaySoptButton
import org.sopt.play.core.designsystem.components.button.PlaySoptButtonType
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptModal(
    title: String,
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier,
    cancelText: String = "취소",
    confirmText: String = "삭제",
) {
    Column(
        modifier = modifier
            .background(color = PlaySoptTheme.colors.white, shape = RoundedCornerShape(12.dp))
            .padding(all = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 20.dp, alignment = Alignment.CenterVertically)
    ) {
        Text(
            text = title,
            color = PlaySoptTheme.colors.gray6,
            textAlign = TextAlign.Center,
            style = PlaySoptTheme.typography.m18,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            PlaySoptButton(
                text = cancelText,
                onClick = onCancel,
                modifier = Modifier.weight(1f),
                type = PlaySoptButtonType.CANCEL,
            )

            PlaySoptButton(
                text = confirmText,
                onClick = onConfirm,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

@Preview
@Composable
private fun PlaySoptModalPreview() {
    PlaySoptTheme {
        Box(modifier = Modifier.background(color = Color.Black)) {
            PlaySoptModal(
                title = "게시글을 삭제할까요?",
                onCancel = {},
                onConfirm = {},
                modifier = Modifier.width(288.dp)
            )
        }
    }
}
