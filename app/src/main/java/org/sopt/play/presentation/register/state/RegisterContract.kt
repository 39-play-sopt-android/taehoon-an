package org.sopt.play.presentation.register.state

data class RegisterUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

sealed interface RegisterAction {
    data object RegisterClicked : RegisterAction
}

sealed interface RegisterEffect {
    data object NavigateToLogin : RegisterEffect
}
