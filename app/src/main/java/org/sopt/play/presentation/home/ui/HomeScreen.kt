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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.play.PlayApplication
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.home.component.banner.HomeBannerRow
import org.sopt.play.presentation.home.component.category.HomeCategoryRow
import org.sopt.play.presentation.home.component.menu.HomeTabMenuRow
import org.sopt.play.presentation.home.component.productcard.HomeScreenProductCard
import org.sopt.play.presentation.home.state.HomeAction
import org.sopt.play.presentation.home.state.HomeUiState

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    val application = LocalContext.current.applicationContext
            as PlayApplication

    val factory = remember(application) {
        HomeViewModelFactory(application.accountManager)
    }

    HomeRouteContent(
        modifier = modifier,
        viewModel = viewModel(factory = factory)
    )
}

@Composable
fun HomeRouteContent(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier,
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 96.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            HomeTabMenuRow(
                selectedMenu = uiState.selectedMenu,
                onMenuClick = { onAction(HomeAction.MenuClicked(it)) },
            )
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            Column {
                HomeBannerRow()

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
                HomeCategoryRow(
                    selectedCategory = uiState.selectedCategory,
                    onCategoryClick = { onAction(HomeAction.CategoryClicked(it)) },
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        items(items = uiState.productList) { product ->
            HomeScreenProductCard(
                product = product,
                onFavoriteClick = {
                    onAction(HomeAction.FavoriteClicked(product))
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PlaySoptTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onAction = { },
        )
    }
}
