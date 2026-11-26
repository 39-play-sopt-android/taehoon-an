package org.sopt.play.presentation.search.ui

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.sopt.play.data.repository.search.SearchRepository
import org.sopt.play.presentation.search.state.SearchAction
import org.sopt.play.presentation.search.state.SearchUiState

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {

    val searchState = TextFieldState()
    val titleState = TextFieldState()
    val contentState = TextFieldState()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: SearchAction) {
        when (action) {
            SearchAction.WriteClicked -> openWriteSheet()
            SearchAction.WriteSheetDismissed -> dismissWriteSheet()
            is SearchAction.PostClicked -> openEditSheet(action.id)
            is SearchAction.PostLongClicked -> openDeleteModal(action.id)
            SearchAction.PostModalDismissed -> dismissPostModal()
            SearchAction.SubmitClicked -> submit()
            SearchAction.DeleteConfirmed -> confirmDelete()
            is SearchAction.SearchClicked -> search(action.query)
        }
    }

    private fun submit() {
        if (_uiState.value.isLoading) return

        val title = titleState.text.toString()
        val content = contentState.text.toString()

        if (
            title.isBlank() || content.isBlank()
        ) return

        _uiState.update {
            it.copy(isWriteSheetVisible = false)
        }
    }

    private fun dismissPostModal() {
        _uiState.update {
            it.copy(selectedPostId = null)
        }
    }

    private fun openWriteSheet() {
        titleState.clearText()
        contentState.clearText()

        _uiState.update {
            it.copy(
                editingPostId = null,
                selectedPostId = null,
                isWriteSheetVisible = true
            )
        }
    }

    private fun dismissWriteSheet() {
        _uiState.update {
            it.copy(
                isWriteSheetVisible = false,
                editingPostId = null
            )
        }
    }

    private fun openEditSheet(id: Long) {
        titleState.setTextAndPlaceCursorAtEnd("제목 수정")
        contentState.setTextAndPlaceCursorAtEnd("기존 본문")

        _uiState.update {
            it.copy(
                editingPostId = id,
                selectedPostId = null,
                isWriteSheetVisible = true
            )
        }
    }

    private fun openDeleteModal(id: Long) {
        _uiState.update {
            it.copy(
                isWriteSheetVisible = false,
                editingPostId = null,
                selectedPostId = id
            )
        }
    }

    private fun confirmDelete() {
        val postId = _uiState.value.selectedPostId ?: return

        Log.d("SearchViewModel", "삭제할 게시글 ID: $postId")

        dismissPostModal()
    }

    private fun search(query: String) {
        val keyword = query.trim()

        Log.d("SearchViewModel", "검색어: $keyword")
    }
}
