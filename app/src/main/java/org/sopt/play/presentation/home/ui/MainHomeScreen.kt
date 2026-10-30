package org.sopt.play.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.home.component.banner.MainBannerRow
import org.sopt.play.presentation.home.component.category.MainCategoryRow
import org.sopt.play.presentation.home.component.menu.MainTabMenuRow
import org.sopt.play.presentation.home.component.productcard.MainScreenProductCard
import org.sopt.play.presentation.home.state.MainHomeAction
import org.sopt.play.presentation.home.state.MainHomeUiState

@Composable
fun MainHomeRoute(
    user: UserModel?,
    viewModel: MainHomeViewModel = viewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.onAction(MainHomeAction.UserNameChanged(user?.name ?: "김솝트"))
    }

    MainHomeScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun MainHomeScreen(
    uiState: MainHomeUiState,
    onAction: (MainHomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 96.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            MainTabMenuRow(
                selectedMenu = uiState.selectedMenu,
                onMenuClick = { onAction(MainHomeAction.MenuClicked(it)) },
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                MainBannerRow()

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                Text(
                    text = "${uiState.userName}님을 위한 추천 아이템",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = PlaySoptTheme.colors.black,
                    style = PlaySoptTheme.typography.m18,
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                MainCategoryRow(
                    selectedCategory = uiState.selectedCategory,
                    onCategoryClick = { onAction(MainHomeAction.CategoryClicked(it)) },
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        items(items = uiState.productList) { product ->
            MainScreenProductCard(
                product = product,
                onFavoriteClick = {
                    onAction(MainHomeAction.FavoriteClicked(product))
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainHomeScreenPreview() {
    PlaySoptTheme {
        MainHomeScreen(
            uiState = MainHomeUiState(),
            onAction = { },
        )
    }
}
