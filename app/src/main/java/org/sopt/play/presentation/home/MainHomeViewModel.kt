package org.sopt.play.presentation.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainHomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainHomeUiState())
    val uiState: StateFlow<MainHomeUiState> = _uiState.asStateFlow()

    fun onAction(action: MainHomeAction) {
        when (action) {
            is MainHomeAction.UserNameChanged -> {
                _uiState.update {
                    it.copy(userName = action.name)
                }
            }

            is MainHomeAction.MenuClicked -> {
                _uiState.update {
                    it.copy(selectedMenu = action.menu)
                }
            }

            is MainHomeAction.CategoryClicked -> {
                _uiState.update {
                    it.copy(selectedCategory = action.category)
                }
            }

            is MainHomeAction.FavoriteClicked -> {
                _uiState.update {
                    it.copy(productList = it.productList.map { product ->
                        if (product == action.product) {
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
