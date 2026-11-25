data class LoginUiState(
    val isCheckingSession: Boolean = true,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

sealed interface LoginAction {
    data object LoginClicked : LoginAction
    data object RegisterClicked : LoginAction
    data object ErrorMessageToast : LoginAction
}

sealed interface LoginEffect {
    data object NavigateToHome : LoginEffect
    data object NavigateToRegister : LoginEffect
}
