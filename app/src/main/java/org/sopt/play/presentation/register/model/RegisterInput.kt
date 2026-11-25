package org.sopt.play.presentation.register.model

import android.util.Patterns

data class RegisterInput(
    val name: String,
    val email: String,
    val password: String,
    val passwordCheck: String,
) {
    val isEmailError: Boolean
        get() = email.isNotBlank() &&
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordError: Boolean
        get() = password.isNotBlank() && password.length < 6

    val isPasswordCheckError: Boolean
        get() = passwordCheck.isNotBlank() &&
            passwordCheck != password

    val isValid: Boolean
        get() = name.isNotBlank() &&
            email.isNotBlank() &&
            password.isNotBlank() &&
            passwordCheck.isNotBlank() &&
            !isEmailError &&
            !isPasswordError &&
            !isPasswordCheckError
}
