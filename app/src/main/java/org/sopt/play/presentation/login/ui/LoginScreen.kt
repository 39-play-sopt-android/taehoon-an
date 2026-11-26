package org.sopt.play.presentation.login.ui

import LoginAction
import LoginEffect
import LoginUiState
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import org.sopt.play.PlayApplication
import org.sopt.play.core.designsystem.components.textfield.PlaySoptPasswordTextField
import org.sopt.play.core.designsystem.components.textfield.PlaySoptTextField
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.login.component.LoginScreenBottomComponent
import org.sopt.play.presentation.login.model.LoginInput

@Composable
fun LoginRoute(
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val application = LocalContext.current.applicationContext
            as PlayApplication

    val factory = remember(application) {
        LoginViewModelFactory(
            application.loginManager,
            application.loginRepository
            )
    }

    LoginRouteContent(
        navigateToHome = navigateToHome,
        navigateToRegister = navigateToRegister,
        modifier = modifier,
        viewModel = viewModel(factory = factory),
    )
}

@Composable
private fun LoginRouteContent(
    navigateToHome: () -> Unit,
    navigateToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(
            Lifecycle.State.STARTED,
        ) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    LoginEffect.NavigateToHome -> navigateToHome()
                    LoginEffect.NavigateToRegister -> navigateToRegister()
                }
            }
        }
    }

    LaunchedEffect(uiState.errorMessage) {
        if (uiState.errorMessage != null) {
            Toast.makeText(
                context,
                uiState.errorMessage,
                Toast.LENGTH_SHORT,
            ).show()

            viewModel.onAction(LoginAction.ErrorMessageToast)
        }
    }

    LoginScreen(
        uiState = uiState,
        emailState = viewModel.emailState,
        passwordState = viewModel.passwordState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun LoginScreen(
    uiState: LoginUiState,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onAction: (LoginAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val focusManager = LocalFocusManager.current

    val input = LoginInput(
        email = emailState.text.toString(),
        password = passwordState.text.toString(),
    )

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
                state = emailState,
                label = "이메일 주소",
                placeholder = "abc@email.com",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                isError = input.isEmailError,
                errorLabel = "올바른 이메일을 입력해주세요.",
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
                isError = input.isPasswordError,
                errorLabel = "비밀번호는 6자 이상 입력해주세요.",
            )

            Spacer(modifier = Modifier.height(40.dp))

            LoginScreenBottomComponent(
                enabled = input.isValid && !uiState.isLoading,
                onButtonClick = {
                    focusManager.clearFocus()
                    onAction(LoginAction.LoginClicked)
                },
                onRegisterClick = {
                    onAction(LoginAction.RegisterClicked)
                },
                modifier = Modifier,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    val emailState = rememberTextFieldState()
    val passwordState = rememberTextFieldState()

    PlaySoptTheme {
        LoginScreen(
            uiState = LoginUiState(),
            emailState = emailState,
            passwordState = passwordState,
            onAction = {},
        )
    }
}
