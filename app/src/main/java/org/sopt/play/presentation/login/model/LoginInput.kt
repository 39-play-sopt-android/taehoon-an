package org.sopt.play.presentation.login.model

import android.util.Patterns

data class LoginInput(
    val email: String,
    val password: String,
) {
    val isEmailError: Boolean
        get() = email.isNotBlank() &&
                !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordError: Boolean
        get() = password.isNotBlank() && password.length < 6

    val isValid: Boolean
        get() = email.isNotBlank() &&
                password.isNotBlank() &&
                !isEmailError &&
                !isPasswordError
}
