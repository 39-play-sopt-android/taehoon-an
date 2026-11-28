package org.sopt.play.presentation.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.play.core.local.login.LoginManager
import org.sopt.play.data.repository.login.LoginRepository

class LoginViewModelFactory(
    private val loginManager: LoginManager,
    private val repository: LoginRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(
                loginManager,
                repository
            ) as T
        }

        throw IllegalArgumentException(
            "지원하지 않는 ViewModel: ${modelClass.name}",
        )
    }
}
