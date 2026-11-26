package org.sopt.play.presentation.search.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.PlayApplication
import org.sopt.play.core.designsystem.components.modal.PlaySoptModal
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.data.model.search.Post
import org.sopt.play.presentation.search.component.bottomsheet.PlaySoptBottomSheet
import org.sopt.play.presentation.search.component.card.PlaySoptSearchScreenCard
import org.sopt.play.presentation.search.component.floatingbutton.PlaySoptFloatingButton
import org.sopt.play.presentation.search.component.textfield.PlaySoptSearchTextField
import org.sopt.play.presentation.search.state.SearchAction
import org.sopt.play.presentation.search.state.SearchEffect
import org.sopt.play.presentation.search.util.PostDateFormatter

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier
) {
    val application = LocalContext.current.applicationContext
            as PlayApplication

    val factory = remember(application) {
        SearchViewModelFactory(
            loginManager = application.loginManager,
            repository = application.searchRepository
        )
    }

    SearchRouteContent(
        modifier = modifier,
        viewModel = viewModel(factory = factory)
    )
}

@Composable
fun SearchRouteContent(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val errorMessage = uiState.errorMessage

    val listState = rememberLazyListState()
    val lifecycleOwner = LocalLifecycleOwner.current

    var pendingScrollPostId by remember {
        mutableStateOf<Long?>(null)
    }

    val title = viewModel.titleState.text.toString()
    val content = viewModel.contentState.text.toString()

    val originalPost = uiState.posts.find {
        it.id == uiState.editingPostId
    }

    val isChanged = if (uiState.editingPostId == null) {
        true
    } else {
        originalPost != null &&
                (title != originalPost.title || content != originalPost.content)
    }

    val isSubmitEnabled =
        title.isNotBlank() &&
                content.isNotBlank() &&
                !uiState.isLoading &&
                isChanged

    LaunchedEffect(errorMessage) {
        if (errorMessage != null) {
            Toast.makeText(
                context,
                errorMessage,
                Toast.LENGTH_SHORT,
            ).show()

            viewModel.onAction(SearchAction.ErrorMessageShown)
        }
    }

    LaunchedEffect(viewModel, lifecycleOwner) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.effect.collect { effect ->
                when (effect) {
                    is SearchEffect.ScrollToTop -> {
                        pendingScrollPostId = effect.firstPostId
                    }
                }
            }
        }
    }

    LaunchedEffect(uiState.posts, pendingScrollPostId) {
        val expectedPostId = pendingScrollPostId

        if (
            expectedPostId != null &&
            uiState.posts.firstOrNull()?.id == expectedPostId
        ) {
            listState.scrollToItem(
                index = 0,
                scrollOffset = 0,
            )

            pendingScrollPostId = null
        }
    }

    SearchScreen(
        posts = uiState.posts,
        listState = listState,
        searchTextFieldState = viewModel.searchState,
        onSearchClick = { viewModel.onAction(SearchAction.SearchClicked(query = viewModel.searchState.text.toString())) },
        onCardClick = { id -> viewModel.onAction(SearchAction.PostClicked(id)) },
        onCardLongClick = { id -> viewModel.onAction(SearchAction.PostLongClicked(id)) },
        onFloatingButtonClick = { viewModel.onAction(SearchAction.WriteClicked) },
        modifier = modifier
    )

    if (uiState.isWriteSheetVisible) {
        PlaySoptBottomSheet(
            titleState = viewModel.titleState,
            contentState = viewModel.contentState,
            onDismissRequest = {
                viewModel.onAction(SearchAction.WriteSheetDismissed)
            },
            onCompleteClick = {
                viewModel.onAction(SearchAction.SubmitClicked)
            },
            enabled = isSubmitEnabled
        )
    }

    if (uiState.selectedPostId != null) {
        Dialog(
            onDismissRequest = {
                viewModel.onAction(SearchAction.PostModalDismissed)
            },
        ) {
            PlaySoptModal(
                title = "게시글을 삭제할까요?",
                onCancel = {
                    viewModel.onAction(SearchAction.PostModalDismissed)
                },
                onConfirm = {
                    viewModel.onAction(SearchAction.DeleteConfirmed)
                },
                modifier = Modifier.padding(horizontal = 36.dp)
            )
        }
    }
}

@Composable
fun SearchScreen(
    posts: List<Post>,
    listState: LazyListState,
    searchTextFieldState: TextFieldState,
    onSearchClick: () -> Unit,
    onCardClick: (Long) -> Unit,
    onCardLongClick: (Long) -> Unit,
    onFloatingButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            PlaySoptFloatingButton(onClick = onFloatingButtonClick)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = PlaySoptTheme.colors.white)
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PlaySoptSearchTextField(
                state = searchTextFieldState,
                onSearchClick = onSearchClick,
                modifier = Modifier.padding(vertical = 12.dp),
                placeholder = "검색어를 입력해주세요."
            )

            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp)
            ) {
                items(
                    items = posts,
                    key = { it.id },
                ) { post ->
                    PlaySoptSearchScreenCard(
                        title = post.title,
                        content = post.content,
                        author = post.author,
                        createdAt = PostDateFormatter.format(post.createdAt),
                        onClick = { onCardClick(post.id) },
                        onLongClick = { onCardLongClick(post.id) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    PlaySoptTheme {
        val posts = List(10) { index ->
            Post(
                id = index.toLong(),
                title = "제목",
                content = "본문입니다.",
                author = "작성자",
                createdAt = "YYYY.MM.DD",
                updatedAt = "YYYY.MM.DD",
            )
        }

        SearchScreen(
            posts = posts,
            listState = LazyListState(),
            searchTextFieldState = TextFieldState(),
            onSearchClick = { },
            onCardClick = { },
            onCardLongClick = { },
            onFloatingButtonClick = { },
        )
    }
}
