package org.sopt.play.presentation.search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.sopt.play.core.local.login.LoginManager
import org.sopt.play.data.repository.search.SearchRepository

class SearchViewModelFactory(
    private val loginManager: LoginManager,
    private val repository: SearchRepository,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass == SearchViewModel::class.java) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(
                loginManager,
                repository
            ) as T
        }

        throw IllegalArgumentException(
            "지원하지 않는 ViewModel: ${modelClass.name}",
        )
    }
}
