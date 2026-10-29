package org.sopt.play.presentation.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.sopt.play.presentation.login.state.LoginAction
import org.sopt.play.presentation.login.state.LoginEffect
import org.sopt.play.presentation.login.state.LoginUiState

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<LoginEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.LoginClicked -> requestLogin()

            LoginAction.RegisterClicked -> {
                viewModelScope.launch {
                    _effect.send(LoginEffect.NavigateToRegister)
                }
            }
        }
    }

    private fun requestLogin() {
        val state = _uiState.value
        if (!state.isLoginEnabled) return

        val email = state.emailState.text.toString()
        val password = state.passwordState.text.toString()

        viewModelScope.launch {
            _effect.send(
                LoginEffect.RequestLogin(
                    email = email,
                    password = password,
                ),
            )
        }
    }
}
