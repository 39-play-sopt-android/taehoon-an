package org.sopt.play.presentation.register.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import org.sopt.play.core.designsystem.components.PlaySoptButton
import org.sopt.play.core.designsystem.components.PlaySoptPasswordTextField
import org.sopt.play.core.designsystem.components.PlaySoptTextField
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.register.state.RegisterAction
import org.sopt.play.presentation.register.state.RegisterEffect
import org.sopt.play.presentation.register.state.RegisterUiState

@Composable
fun RegisterRoute(
    onRegisterClick: (UserModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnRegisterClick by rememberUpdatedState(onRegisterClick)

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(
            Lifecycle.State.STARTED,
        ) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is RegisterEffect.RequestRegister -> {
                        currentOnRegisterClick(effect.user)
                    }
                }
            }
        }
    }

    RegisterScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
fun RegisterScreen(
    uiState: RegisterUiState,
    onAction: (RegisterAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PlaySoptTheme.colors.white
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(state = rememberScrollState())
                    .padding(top = 60.dp, bottom = 40.dp),
            ) {
                Text(
                    text = "이메일로 회원가입",
                    color = PlaySoptTheme.colors.black,
                    style = PlaySoptTheme.typography.b28
                )

                Spacer(modifier = Modifier.height(40.dp))

                PlaySoptTextField(
                    state = uiState.nameState,
                    label = "이름",
                    placeholder = "홍길동",
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                )

                Spacer(modifier = Modifier.height(32.dp))

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
                    imeAction = ImeAction.Next,
                    isError = uiState.isPasswordError,
                    errorLabel = "비밀번호는 6자 이상 입력해주세요.",
                )

                Spacer(modifier = Modifier.height(32.dp))

                PlaySoptPasswordTextField(
                    state = uiState.passwordCheckState,
                    label = "비밀번호 확인",
                    placeholder = "6자 이상의 비밀번호",
                    onKeyboardAction = { performDefaultAction ->
                        performDefaultAction()
                        focusManager.clearFocus()
                    },
                    imeAction = ImeAction.Done,
                    isError = uiState.isPasswordCheckError,
                    errorLabel = "비밀번호와 동일하게 입력해주세요.",
                )
            }

            PlaySoptButton(
                text = "회원가입",
                modifier = Modifier.padding(
                    top = 16.dp,
                    bottom = 16.dp,
                ),
                onClick = { onAction(RegisterAction.RegisterClicked) },
                enabled = uiState.isRegisterEnabled
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    val uiState = remember { RegisterUiState() }

    PlaySoptTheme {
        RegisterScreen(
            uiState = uiState,
            onAction = {},
            modifier = Modifier.fillMaxSize(),
        )
    }
}
