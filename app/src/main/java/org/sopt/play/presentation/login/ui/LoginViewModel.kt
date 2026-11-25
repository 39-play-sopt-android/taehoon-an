package org.sopt.play.presentation.login.ui

import LoginAction
import LoginEffect
import LoginUiState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.core.local.login.AccountManager
import org.sopt.play.presentation.login.model.LoginInput
import java.io.IOException

class LoginViewModel(
    private val accountManager: AccountManager
) : ViewModel() {
    val emailState = TextFieldState()
    val passwordState = TextFieldState()

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<LoginEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        checkAutoLogin()
    }

    fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.LoginClicked -> login()
            LoginAction.RegisterClicked -> {
                if(
                    _uiState.value.isCheckingSession || _uiState.value.isLoading
                ) return

                viewModelScope.launch {
                    _effect.send(LoginEffect.NavigateToRegister)
                }
            }
            LoginAction.ErrorMessageToast -> {
                _uiState.update {
                    it.copy(errorMessage = null)
                }
            }
        }
    }

    private fun login() {
        val state = _uiState.value
        if (state.isCheckingSession || state.isLoading) return

        val input = LoginInput(
            email = emailState.text.toString(),
            password = passwordState.text.toString(),
        )
        if (!input.isValid) return

        _uiState.update {
            it.copy(isLoading = true, errorMessage = null)
        }

        viewModelScope.launch {
            try {
                val success = accountManager.login(
                    id = input.email,
                    password = input.password,
                )

                if (success) {
                    _effect.send(LoginEffect.NavigateToHome)
                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage =
                                "이메일 또는 비밀번호가 올바르지 않아요.",
                        )
                    }
                }
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "로그인 정보를 읽거나 저장하지 못했어요.",
                    )
                }
            }
        }
    }

    private fun checkAutoLogin() {
        viewModelScope.launch {
            try {
                if (accountManager.isLoggedIn()) {
                    _effect.send(LoginEffect.NavigateToHome)
                } else {
                    _uiState.update {
                        it.copy(isCheckingSession = false)
                    }
                }
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(
                        isCheckingSession = false,
                        errorMessage = "저장된 로그인 정보를 읽지 못했어요.",
                    )
                }
            }
        }
    }
}
