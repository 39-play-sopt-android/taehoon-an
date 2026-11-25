package org.sopt.play.presentation.home.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.play.presentation.home.state.HomeAction
import org.sopt.play.presentation.home.state.HomeUiState

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            is HomeAction.UserNameChanged -> {
                _uiState.update {
                    it.copy(userName = action.name)
                }
            }

            is HomeAction.MenuClicked -> {
                _uiState.update {
                    it.copy(selectedMenu = action.menu)
                }
            }

            is HomeAction.CategoryClicked -> {
                _uiState.update {
                    it.copy(selectedCategory = action.category)
                }
            }

            is HomeAction.FavoriteClicked -> {
                _uiState.update {
                    it.copy(productList = it.productList.map { product ->
                        if (product.productId == action.product.productId) {
                            product.copy(isFavorite = !product.isFavorite)
                        } else {
                            product
                        }
                    }
                    )
                }
            }
        }
    }
}
