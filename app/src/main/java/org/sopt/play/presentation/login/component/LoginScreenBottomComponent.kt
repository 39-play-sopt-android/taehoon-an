package org.sopt.play.presentation.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.components.PlaySoptButton
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

@Composable
fun LoginScreenBottomComponent(
    enabled: Boolean,
    onButtonClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val style = PlaySoptTheme.typography.m14

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        PlaySoptButton(
            text = "로그인",
            onClick = onButtonClick,
            enabled = enabled,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "아직 계정이 없으신가요?",
                color = PlaySoptTheme.colors.gray3,
                style = style
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "회원가입하기",
                modifier = Modifier.noRippleClickable(
                    onClick = onRegisterClick
                ),
                color = PlaySoptTheme.colors.gray6,
                style = style
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenBottomPreview() {
    PlaySoptTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            LoginScreenBottomComponent(
                enabled = true,
                onButtonClick = {},
                onRegisterClick = {}
            )
        }
    }
}
