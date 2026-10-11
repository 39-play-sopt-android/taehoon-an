package org.sopt.play.core.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
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
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.components.textfield.PlaySoptTextFieldLayout
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
    var isFocused by remember { mutableStateOf(false) }
    val textFieldTextStyle = PlaySoptTheme.typography.m18
    val shape = RoundedCornerShape(12.dp)
    val borderColor = when {
        isError -> PlaySoptTheme.colors.red
        isFocused -> PlaySoptTheme.colors.gray5
        else -> PlaySoptTheme.colors.gray2
    }

    PlaySoptTextFieldLayout(
        label = label,
        modifier = modifier,
        isError = isError,
        errorLabel = errorLabel,
    ) {
        BasicTextField(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { isFocused = it.isFocused }
                .clip(shape)
                .background(PlaySoptTheme.colors.white)
                .border(width = 2.dp, color = borderColor, shape = shape),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
            ),
            onKeyboardAction = onKeyboardAction,
            lineLimits = lineLimits,
            textStyle = textFieldTextStyle.copy(color = PlaySoptTheme.colors.gray5),
            decorator = { innerTextField ->
                Box(modifier = Modifier.padding(16.dp)) {
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
    }
}
