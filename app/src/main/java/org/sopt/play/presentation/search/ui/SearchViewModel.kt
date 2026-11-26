package org.sopt.play.presentation.search.ui

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.sopt.play.core.local.login.LoginManager
import org.sopt.play.data.dto.search.request.PostCreateRequest
import org.sopt.play.data.dto.search.request.PostEditRequest
import org.sopt.play.data.repository.search.SearchRepository
import org.sopt.play.presentation.search.state.SearchAction
import org.sopt.play.presentation.search.state.SearchEffect
import org.sopt.play.presentation.search.state.SearchUiState
import kotlin.coroutines.cancellation.CancellationException

class SearchViewModel(
    private val loginManager: LoginManager,
    private val repository: SearchRepository
) : ViewModel() {

    val searchState = TextFieldState()
    val titleState = TextFieldState()
    val contentState = TextFieldState()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<SearchEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    private var searchsJob: Job? = null
    private var mutationJob: Job? = null

    init {
        getPosts("")
    }

    fun onAction(action: SearchAction) {
        when (action) {
            SearchAction.WriteClicked -> openWriteSheet()
            SearchAction.WriteSheetDismissed -> if (mutationJob?.isActive != true) dismissWriteSheet()
            is SearchAction.PostClicked -> openEditSheet(action.id)
            is SearchAction.PostLongClicked -> openDeleteModal(action.id)
            SearchAction.PostModalDismissed -> dismissPostModal()
            SearchAction.SubmitClicked -> submit()
            SearchAction.DeleteConfirmed -> confirmDelete()
            is SearchAction.SearchClicked -> getPosts(action.query)
            SearchAction.ErrorMessageShown -> {
                _uiState.update {
                    it.copy(errorMessage = null)
                }
            }
        }
    }

    private fun submit() {
        if (mutationJob?.isActive == true) return

        val state = _uiState.value
        if (!state.isWriteSheetVisible || state.isLoading) return

        val title = titleState.text.toString()
        val content = contentState.text.toString()
        val editingPostId = state.editingPostId

        if (
            title.isBlank() ||
            content.isBlank()
        ) return

        if (editingPostId != null) {
            val original = state.posts.find {
                it.id == editingPostId
            } ?: return

            if (
                title == original.title &&
                content == original.content
            ) return
        }

        searchsJob?.cancel()

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
            )
        }

        mutationJob = viewModelScope.launch {
            try {
                if (editingPostId == null) {
                    val author = loginManager.getName()

                    if (author.isNullOrBlank()) {
                        _uiState.update {
                            it.copy(
                                errorMessage = "작성자 정보를 찾을 수 없어요.",
                            )
                        }
                        return@launch
                    }

                    repository.createPost(
                        request = PostCreateRequest(
                            title = title,
                            content = content,
                            author = author,
                        ),
                    )
                } else {
                    repository.editPost(
                        postId = editingPostId,
                        request = PostEditRequest(
                            title = title,
                            content = content,
                        ),
                    )
                }

                dismissWriteSheet()

                val refreshed = refreshPosts()

                if (refreshed && editingPostId == null) {
                    val firstPostId = _uiState.value.posts.firstOrNull()?.id

                    if (firstPostId != null) {
                        _effect.send(
                            SearchEffect.ScrollToTop(
                                firstPostId = firstPostId,
                            ),
                        )
                    }
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        errorMessage = if (editingPostId == null) {
                            "게시글을 작성하지 못했어요."
                        } else {
                            "게시글을 수정하지 못했어요."
                        },
                    )
                }
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private fun dismissPostModal() {
        if (mutationJob?.isActive == true) return

        _uiState.update {
            it.copy(selectedPostId = null)
        }
    }

    private fun openWriteSheet() {
        if (mutationJob?.isActive == true) return

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
        if (mutationJob?.isActive == true) return

        if (_uiState.value.isLoading) return

        val post = _uiState.value.posts.find { it.id == id } ?: return

        titleState.setTextAndPlaceCursorAtEnd(post.title)
        contentState.setTextAndPlaceCursorAtEnd(post.content)

        _uiState.update {
            it.copy(
                editingPostId = id,
                selectedPostId = null,
                isWriteSheetVisible = true,
            )
        }
    }

    private fun openDeleteModal(id: Long) {
        if (mutationJob?.isActive == true) return

        _uiState.update {
            it.copy(
                isWriteSheetVisible = false,
                editingPostId = null,
                selectedPostId = id
            )
        }
    }

    private fun confirmDelete() {
        if (mutationJob?.isActive == true) return

        val postId = _uiState.value.selectedPostId ?: return

        searchsJob?.cancel()

        _uiState.update {
            it.copy(
                isLoading = true,
                errorMessage = null,
            )
        }

        mutationJob = viewModelScope.launch {
            try {
                repository.deletePost(postId)

                _uiState.update { state ->
                    state.copy(
                        selectedPostId = null,
                        posts = state.posts.filterNot { it.id == postId },
                    )
                }

                refreshPosts()
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("SearchViewModel", "게시글 삭제 실패: id=$postId", e)

                _uiState.update {
                    it.copy(
                        errorMessage = "게시글을 삭제하지 못했어요.",
                    )
                }
            } finally {
                _uiState.update {
                    it.copy(isLoading = false)
                }
            }
        }
    }

    private suspend fun refreshPosts(): Boolean {
        return try {
            val keyword = _uiState.value.keyword

            val page = repository.getPosts(
                keyword = keyword.takeIf { it.isNotEmpty() },
                cursor = null,
                size = 30,
            )

            _uiState.update {
                it.copy(posts = page.posts)
            }

            true
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    errorMessage = "처리는 완료됐지만 목록을 불러오지 못했어요.",
                )
            }

            false
        }
    }

    private fun getPosts(query: String) {
        if (mutationJob?.isActive == true) return

        val keyword = query.trim()

        if (keyword.isNotEmpty() && keyword.length !in 2..50) {
            _uiState.update {
                it.copy(
                    errorMessage = "검색어는 2~50자로 입력해주세요.",
                )
            }
            return
        }

        searchsJob?.cancel()

        _uiState.update {
            it.copy(
                keyword = keyword,
                isLoading = true,
                errorMessage = null,
            )
        }

        searchsJob = viewModelScope.launch {
            try {
                val page = repository.getPosts(
                    keyword = keyword.takeIf { it.isNotEmpty() },
                    cursor = null,
                    size = 30,
                )

                _uiState.update {
                    it.copy(
                        posts = page.posts,
                        isLoading = false,
                    )
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "게시글을 불러오지 못했어요.",
                    )
                }
            }
        }
    }
}
