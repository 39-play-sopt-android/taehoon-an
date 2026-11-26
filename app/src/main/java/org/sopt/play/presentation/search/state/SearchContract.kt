package org.sopt.play.presentation.search.state

import org.sopt.play.data.model.search.Post

data class SearchUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isWriteSheetVisible: Boolean = false,
    val selectedPostId: Long? = null,
    val editingPostId: Long? = null,
    val posts: List<Post> = emptyList(),
    val keyword: String = ""
)

sealed interface SearchAction {
    data class PostClicked(val id: Long) : SearchAction
    data class PostLongClicked(val id: Long) : SearchAction
    data class SearchClicked(val query: String) : SearchAction
    data object WriteClicked : SearchAction
    data object WriteSheetDismissed : SearchAction
    data object SubmitClicked : SearchAction
    data object PostModalDismissed : SearchAction
    data object DeleteConfirmed : SearchAction
    data object ErrorMessageShown : SearchAction
}

sealed interface SearchEffect {
    data class ScrollToTop(val firstPostId: Long) : SearchEffect
}
