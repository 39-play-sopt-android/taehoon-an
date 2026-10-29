package org.sopt.play.presentation.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.register.state.RegisterAction
import org.sopt.play.presentation.register.state.RegisterEffect
import org.sopt.play.presentation.register.state.RegisterUiState

class RegisterViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<RegisterEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onAction(action: RegisterAction) {
        when (action) {
            RegisterAction.RegisterClicked -> requestRegister()
        }
    }

    private fun requestRegister() {
        val state = _uiState.value
        if (!state.isRegisterEnabled || state.isLoading) return

        val name = state.nameState.text.toString()
        val email = state.emailState.text.toString()
        val password = state.passwordState.text.toString()

        viewModelScope.launch {
            _effect.send(
                RegisterEffect.RequestRegister(
                    UserModel(
                        name = name,
                        email = email,
                        password = password,
                    )
                ),
            )
        }
    }
}
