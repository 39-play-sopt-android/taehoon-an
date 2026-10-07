package org.sopt.play.core.designsystem.components

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
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun PlaySoptPasswordTextField(
    state: TextFieldState,
    label: String,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Email,
    imeAction: ImeAction = ImeAction.Default,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
    isError: Boolean = false,
    errorLabel: String? = null,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        var isFocused by remember { mutableStateOf(false) }

        val textFieldTextStyle = PlaySoptTheme.typography.m18
        val borderColor = when {
            isError -> PlaySoptTheme.colors.red
            isFocused -> PlaySoptTheme.colors.gray5
            else -> PlaySoptTheme.colors.gray2
        }
        val shape = RoundedCornerShape(12.dp)
        val startPadding = 6.dp

        Text(
            text = label,
            modifier = Modifier.padding(start = startPadding),
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
            textStyle = textFieldTextStyle.copy(color = PlaySoptTheme.colors.gray5),
            lineLimits = lineLimits,
            decorator = { innerTextField ->
                Box(
                    modifier = Modifier.padding(16.dp),
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

        if (isError && errorLabel != null) {
            Text(
                text = errorLabel,
                modifier = Modifier.padding(start = startPadding),
                color = PlaySoptTheme.colors.red,
                style = PlaySoptTheme.typography.m14
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MomensTextFieldPreview() {
    PlaySoptTheme {
        val notEnteredState = rememberTextFieldState()
        val enteredState = rememberTextFieldState(initialText = "text")
        val textCountState = rememberTextFieldState(initialText = "text")
        val textErrorState = rememberTextFieldState(initialText = "texttexttexttext")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            PlaySoptTextField(
                state = notEnteredState,
                label = "id",
                modifier = Modifier,
                placeholder = "id 입력 바람"
            )

            PlaySoptTextField(
                state = enteredState,
                label = "id",
                modifier = Modifier,
                placeholder = "id 입력 바람"
            )
        }
    }
}
