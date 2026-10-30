package org.sopt.play.presentation.login.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.core.designsystem.components.PlaySoptPasswordTextField
import org.sopt.play.core.designsystem.components.PlaySoptTextField
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.login.component.LoginScreenBottomComponent
import org.sopt.play.presentation.login.state.LoginAction
import org.sopt.play.presentation.login.state.LoginEffect
import org.sopt.play.presentation.login.state.LoginUiState

@Composable
fun LoginRoute(
    onLoginClick: (UserModel) -> Unit,
    onRegisterClick: () -> Unit,
    viewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnLoginClick by rememberUpdatedState(onLoginClick)
    val currentOnRegisterClick by rememberUpdatedState(onRegisterClick)

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(
            Lifecycle.State.STARTED,
        ) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is LoginEffect.RequestLogin -> {
                        currentOnLoginClick(
                            UserModel(
                                email = effect.email,
                                password = effect.password,
                            ),
                        )
                    }

                    LoginEffect.NavigateToRegister -> {
                        currentOnRegisterClick()
                    }
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PlaySoptTheme.colors.white
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(
                    start = 16.dp,
                    top = 60.dp,
                    end = 16.dp,
                ),
        ) {
            Text(
                text = "이메일로 로그인하기",
                color = PlaySoptTheme.colors.black,
                style = PlaySoptTheme.typography.b28
            )

            Spacer(modifier = Modifier.height(40.dp))

            PlaySoptTextField(
                state = uiState.emailState,
                label = "이메일 주소",
                placeholder = "abc@email.com",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                isError = uiState.isEmailError,
                errorLabel = "올바른 이메일을 입력해주세요.",
            )

            Spacer(modifier = Modifier.height(32.dp))

            PlaySoptPasswordTextField(
                state = uiState.passwordState,
                label = "비밀번호",
                placeholder = "6자 이상의 비밀번호",
                onKeyboardAction = { performDefaultAction ->
                    performDefaultAction()
                    focusManager.clearFocus()
                },
                imeAction = ImeAction.Done,
                isError = uiState.isPasswordError,
                errorLabel = "비밀번호는 6자 이상 입력해주세요.",
            )

            Spacer(modifier = Modifier.height(40.dp))

            LoginScreenBottomComponent(
                enabled = uiState.isLoginEnabled,
                onButtonClick = {
                    focusManager.clearFocus()
                    onAction(LoginAction.LoginClicked)
                },
                onRegisterClick = { onAction(LoginAction.RegisterClicked) },
                modifier = Modifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    val uiState = remember { LoginUiState() }
    PlaySoptTheme {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            onAction = { },
        )
    }
}
