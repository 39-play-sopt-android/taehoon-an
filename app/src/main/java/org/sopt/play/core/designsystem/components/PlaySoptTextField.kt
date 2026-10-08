package org.sopt.play.core.designsystem.components

import android.util.Patterns
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptTextField(
    state: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Email,
    imeAction: ImeAction = ImeAction.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    isError: Boolean = false,
    errorLabel: String? = null,
) {
    var isFocused by remember { mutableStateOf(value = false) }

    val textFieldTextStyle = PlaySoptTheme.typography.m18
    val borderColor = when {
        isError -> PlaySoptTheme.colors.red
        isFocused -> PlaySoptTheme.colors.gray5
        else -> PlaySoptTheme.colors.gray2
    }
    val shape = RoundedCornerShape(size = 12.dp)
    val startPadding = 6.dp
    val componentPadding = 6.dp

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(start = startPadding, bottom = componentPadding),
            color = PlaySoptTheme.colors.gray6,
            style = PlaySoptTheme.typography.sb16
        )

        BasicTextField(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .clip(shape = shape)
                .background(color = PlaySoptTheme.colors.white)
                .border(
                    width = 2.dp,
                    color = borderColor,
                    shape = shape,
                ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            onKeyboardAction = onKeyboardAction,
            textStyle = textFieldTextStyle.copy(color = PlaySoptTheme.colors.gray5),
            lineLimits = lineLimits,
            decorator = { innerTextField ->
                Box(
                    modifier = Modifier.padding(all = 16.dp),
                ) {
                    if (state.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = PlaySoptTheme.colors.gray2,
                            style = textFieldTextStyle,
                        )
                    }
                    innerTextField()
                }
            },
        )

        AnimatedVisibility(
            visible = isError && errorLabel != null,
            enter = fadeIn(
                animationSpec = tween(durationMillis = 200),
            ) + expandVertically(
                animationSpec = tween(durationMillis = 200),
                expandFrom = Alignment.Top,
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 200),
            ) + shrinkVertically(
                animationSpec = tween(durationMillis = 200),
                shrinkTowards = Alignment.Top,
            ),
        ) {
            Text(
                text = errorLabel.orEmpty(),
                modifier = Modifier.padding(start = startPadding, top = componentPadding),
                color = PlaySoptTheme.colors.red,
                style = PlaySoptTheme.typography.m14
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PlaySoptTextFieldPreview() {
    PlaySoptTheme {
        val emptyState = rememberTextFieldState()
        val validState = rememberTextFieldState(
            initialText = "abc@email.com",
        )
        val invalidState = rememberTextFieldState(
            initialText = "abc.com",
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            listOf(
                emptyState,
                validState,
                invalidState,
            ).forEach { state ->
                val email = state.text.toString()
                val isError = email.isNotEmpty() &&
                        !Patterns.EMAIL_ADDRESS.matcher(email).matches()


                PlaySoptTextField(
                    state = state,
                    label = "이메일",
                    placeholder = "abc@email.com",
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                    isError = isError,
                    errorLabel = "잘못된 이메일입니다.",
                )
            }
        }
    }
}
