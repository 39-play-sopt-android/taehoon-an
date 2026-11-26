package org.sopt.play.presentation.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.play.core.local.login.LoginManager

class HomeViewModelFactory(
    private val accountManager: LoginManager,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(accountManager) as T
        }

        throw IllegalArgumentException(
            "지원하지 않는 ViewModel: ${modelClass.name}",
        )
    }
}
