package org.sopt.play.presentation.login.state

import android.util.Patterns
import androidx.compose.foundation.text.input.TextFieldState

data class LoginUiState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    val isEmailError: Boolean
        get() {
            val email = emailState.text.toString()

            return email.isNotEmpty() &&
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

    val isPasswordError: Boolean
        get() {
            val password = passwordState.text

            return password.isNotEmpty() && password.length < 6
        }

    val isLoginEnabled: Boolean
        get() = emailState.text.isNotEmpty() &&
                passwordState.text.isNotEmpty() &&
                !isEmailError &&
                !isPasswordError &&
                !isLoading
}

sealed interface LoginAction {
    data object LoginClicked: LoginAction

    data object RegisterClicked: LoginAction
}

sealed interface LoginEffect{
    data class RequestLogin(
        val email: String,
        val password: String,
    ) : LoginEffect

    data object NavigateToRegister : LoginEffect
}
