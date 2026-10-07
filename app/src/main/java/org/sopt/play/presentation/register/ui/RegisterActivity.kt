package org.sopt.play.presentation.register.ui

import android.os.Bundle
import android.util.Patterns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.components.PlaySoptButton
import org.sopt.play.core.designsystem.components.PlaySoptPasswordTextField
import org.sopt.play.core.designsystem.components.PlaySoptTextField
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .background(color = PlaySoptTheme.colors.white)
                            .padding(innerPadding),
                        onRegisterClick = { email, password ->
                            intent.putExtra("email", email)
                            intent.putExtra("password", password)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: (String, String) -> Unit
) {
    val nameState = rememberTextFieldState()
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()
    val passwordCheckState = rememberTextFieldState()
    val focusManager = LocalFocusManager.current

    val isEmailError = emailState.text.toString().isNotEmpty() &&
            !Patterns.EMAIL_ADDRESS.matcher(emailState.text.toString()).matches()
    val isPasswordError = passwordState.text.toString().isNotEmpty() &&
            passwordState.text.toString().length < 6
    val isPasswordCheckError = passwordCheckState.text.toString()
        .isNotEmpty() && passwordCheckState.text.toString() != passwordState.text.toString()

    Column(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(top = 60.dp, bottom = 40.dp),
        ) {
            Text(
                text = "이메일로 회원가입",
                color = PlaySoptTheme.colors.black,
                style = PlaySoptTheme.typography.b28
            )

            Spacer(modifier = Modifier.height(40.dp))

            PlaySoptTextField(
                state = nameState,
                label = "이름",
                placeholder = "홍길동",
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )

            Spacer(modifier = Modifier.height(32.dp))

            PlaySoptTextField(
                state = emailState,
                label = "이메일 주소",
                placeholder = "abc@email.com",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                isError = isEmailError,
                errorLabel = "올바른 이메일을 입력해주세요.",
            )

            Spacer(modifier = Modifier.height(32.dp))

            PlaySoptPasswordTextField(
                state = passwordState,
                label = "비밀번호",
                placeholder = "6자 이상의 비밀번호",
                imeAction = ImeAction.Next,
                isError = isPasswordError,
                errorLabel = "비밀번호는 6자 이상 입력해주세요.",
            )

            Spacer(modifier = Modifier.height(32.dp))

            PlaySoptPasswordTextField(
                state = passwordCheckState,
                label = "비밀번호 확인",
                placeholder = "6자 이상의 비밀번호",
                onKeyboardAction = { performDefaultAction ->
                    performDefaultAction()
                    focusManager.clearFocus()
                },
                imeAction = ImeAction.Done,
                isError = isPasswordCheckError,
                errorLabel = "비밀번호와 동일하게 입력해주세요.",
            )
        }

        PlaySoptButton(
            text = "회원가입",
            modifier = Modifier.padding(
                top = 16.dp,
                bottom = 16.dp,
            ),
            onClick = {
                onRegisterClick(
                    emailState.text.toString(),
                    passwordState.text.toString(),
                )
            },
            enabled = nameState.text.isNotBlank() &&
                    emailState.text.isNotEmpty() &&
                    passwordState.text.isNotEmpty() &&
                    passwordCheckState.text.isNotEmpty() &&
                    !isEmailError &&
                    !isPasswordError &&
                    !isPasswordCheckError,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    PlaySoptTheme {
        RegisterScreen(
            modifier = Modifier.fillMaxSize(),
            onRegisterClick = { email, password -> }
        )
    }
}
