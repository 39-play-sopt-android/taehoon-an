package org.sopt.play.presentation.register.ui

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.core.local.login.LoginManager
import org.sopt.play.presentation.register.model.RegisterInput
import org.sopt.play.presentation.register.state.RegisterAction
import org.sopt.play.presentation.register.state.RegisterEffect
import org.sopt.play.presentation.register.state.RegisterUiState
import java.io.IOException

class RegisterViewModel(
    private val accountManager: LoginManager,
) : ViewModel() {

    val nameState = TextFieldState()
    val emailState = TextFieldState()
    val passwordState = TextFieldState()
    val passwordCheckState = TextFieldState()

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<RegisterEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.RegisterClicked -> register()
        }
    }

    private fun register() {
        if (_uiState.value.isLoading) return

        val input = RegisterInput(
            name = nameState.text.toString(),
            email = emailState.text.toString(),
            password = passwordState.text.toString(),
            passwordCheck = passwordCheckState.text.toString(),
        )
        if (!input.isValid) return

        _uiState.update {
            it.copy(isLoading = true, errorMessage = null)
        }

        viewModelScope.launch {
            try {
                accountManager.saveLogin(
                    id = input.email,
                    password = input.password,
                    name = input.name
                )

                _effect.send(RegisterEffect.NavigateToLogin)
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "회원가입 정보를 저장하지 못했어요.",
                    )
                }
            }
        }
    }
}
