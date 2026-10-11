package org.sopt.play.presentation.login.ui

import android.util.Patterns
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.components.PlaySoptPasswordTextField
import org.sopt.play.core.designsystem.components.PlaySoptTextField
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.login.component.LoginScreenBottomComponent

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val focusManager = LocalFocusManager.current

    val isEmailError by remember(key1 = emailState) {
        derivedStateOf {
            val email = emailState.text.toString()

            email.isNotEmpty() &&
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    val isPasswordError by remember(key1 = passwordState) {
        derivedStateOf {
            val password = passwordState.text

            password.isNotEmpty() && password.length < 6
        }
    }

    Column(
        modifier = modifier.verticalScroll(state = scrollState).padding(start = 16.dp, top = 60.dp, end = 16.dp, bottom = 0.dp),
    ) {
        Text(
            text = "이메일로 로그인하기",
            color = PlaySoptTheme.colors.black,
            style = PlaySoptTheme.typography.b28
        )

        Spacer(modifier = Modifier.height(40.dp))

        PlaySoptTextField(
            state = emailState,
            label = "이메일 주소",
            placeholder = "abc@email.com",
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            isError = isEmailError,
            errorLabel = "올바른 이메일을 입력해주세요..",
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlaySoptPasswordTextField(
            state = passwordState,
            label = "비밀번호",
            placeholder = "6자 이상의 비밀번호",
            onKeyboardAction = { performDefaultAction ->
                performDefaultAction()
                focusManager.clearFocus()
            },
            imeAction = ImeAction.Done,
            isError = isPasswordError,
            errorLabel = "비밀번호는 6자 이상 입력해주세요.",
        )

        Spacer(modifier = Modifier.height(40.dp))

        LoginScreenBottomComponent(
            enabled = emailState.text.isNotEmpty() &&
                    passwordState.text.isNotEmpty() &&
                    !isEmailError &&
                    !isPasswordError,
            onButtonClick = {
                onLoginClick(
                    emailState.text.toString(),
                    passwordState.text.toString(),
                )
            },
            onRegisterClick = onRegisterClick,
            modifier = Modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    PlaySoptTheme {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            onLoginClick = { email, password -> },
            onRegisterClick = { }
        )
    }
}
