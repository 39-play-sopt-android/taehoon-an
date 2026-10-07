package org.sopt.play

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = PlaySoptTheme.colors.white)
                            .padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val focusManager = LocalFocusManager.current

    val isEmailError = emailState.text.toString().isNotEmpty() &&
            !Patterns.EMAIL_ADDRESS.matcher(emailState.text.toString()).matches()
    val isPasswordError = passwordState.text.toString().isNotEmpty() &&
            passwordState.text.toString().length < 6

    Column(
        modifier = modifier.padding(start = 16.dp, top = 60.dp, end = 16.dp, bottom = 0.dp),
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
            errorLabel = "잘못된 이메일입니다.",
        )

        Spacer(modifier = Modifier.height(32.dp))

        PlaySoptPasswordTextField(
            state = passwordState,
            label = "비밀번호",
            placeholder = "6자 이상의 비밀번호",
            onKeyboardAction = { performDefaultAction ->
                performDefaultAction()    // Done 기본 동작: 키보드 숨김
                focusManager.clearFocus() // 포커스 해제 → 테두리 갱신
            },
            imeAction = ImeAction.Done,
            isError = isPasswordError,
            errorLabel = "비밀번호는 6자 이상 입력해주세요.",
        )

        Spacer(modifier = Modifier.height(40.dp))

        LoginScreenBottomComponent(
            enabled = isEmailError || isPasswordError,
            onButtonClick = { },
            onRegisterClick = { },
            modifier = Modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PlaySoptTheme {
        LoginScreen(modifier = Modifier.fillMaxSize())
    }
}
