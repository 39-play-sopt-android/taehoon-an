package org.sopt.play.core.designsystem.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable

@Composable
fun PlaySoptSearchTextField(
    state: TextFieldState,
    onSearchClick:() -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
) {
    var isFocused by remember { mutableStateOf(false) }
    val textFieldTextStyle = PlaySoptTheme.typography.m18
    val shape = RoundedCornerShape(12.dp)
    val borderColor = if(isFocused) PlaySoptTheme.colors.gray5 else PlaySoptTheme.colors.gray2

    BasicTextField(
        state = state,
        modifier = modifier
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
            Row(
                modifier = Modifier.padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
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

                Icon(
                    painter = painterResource(R.drawable.ic_search_default),
                    contentDescription = null,
                    modifier = Modifier.noRippleClickable(onClick = onSearchClick),
                    tint = PlaySoptTheme.colors.gray3,
                )

            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PlaySoptSearchTextFieldPreview() {
    val state = rememberTextFieldState()

    PlaySoptTheme {
        PlaySoptSearchTextField(
            state = state,
            onSearchClick = {},
            placeholder = "검색어를 입력해주세요.",
            modifier = Modifier.padding(16.dp),
        )
    }
}
