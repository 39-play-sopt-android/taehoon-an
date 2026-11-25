package org.sopt.play.presentation.register.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.play.core.local.login.AccountManager

class RegisterViewModelFactory(
    private val accountManager: AccountManager,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == RegisterViewModel::class.java) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(accountManager) as T
        }

        throw IllegalArgumentException(
            "지원하지 않는 ViewModel: ${modelClass.name}",
        )
    }
}
