package org.sopt.play.presentation.register.state

import android.util.Patterns
import androidx.compose.foundation.text.input.TextFieldState
import org.sopt.play.data.model.UserModel

data class RegisterUiState(
    val emailState: TextFieldState = TextFieldState(),
    val passwordState: TextFieldState = TextFieldState(),
    val passwordCheckState: TextFieldState = TextFieldState(),
    val nameState: TextFieldState = TextFieldState(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
) {
    val isEmailError: Boolean
        get() {
            val email = emailState.text.toString()

            return email.isNotBlank() &&
                    !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

    val isPasswordError: Boolean
        get() {
            val password = passwordState.text

            return password.isNotBlank() && password.length < 6
        }

    val isPasswordCheckError: Boolean
        get() {
            val password = passwordState.text.toString()
            val passwordCheck = passwordCheckState.text.toString()

            return passwordCheck.isNotBlank() && passwordCheck != password
        }

    val isRegisterEnabled: Boolean
        get() = nameState.text.isNotBlank() &&
                nameState.text.isNotBlank() &&
                emailState.text.isNotBlank() &&
                passwordState.text.isNotBlank() &&
                passwordCheckState.text.isNotBlank() &&
                !isEmailError &&
                !isPasswordError &&
                !isPasswordCheckError
}

sealed interface RegisterAction {
    data object RegisterClicked: RegisterAction
}

sealed interface RegisterEffect{
    data class RequestRegister(
        val user: UserModel
    ) : RegisterEffect
}
