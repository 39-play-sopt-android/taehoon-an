package org.sopt.play.presentation.main.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.favorite.MainFavoriteScreen
import org.sopt.play.presentation.home.MainHomeRoute
import org.sopt.play.presentation.main.component.MainScreenBottomBar
import org.sopt.play.presentation.main.component.MainScreenTopBar
import org.sopt.play.presentation.my.MainMyScreen

enum class MainTab(
    val label: String,
    @DrawableRes val defaultIcon: Int,
    @DrawableRes val selectedIcon: Int
) {
    HOME(
        label = "홈",
        defaultIcon = R.drawable.ic_home_default,
        selectedIcon = R.drawable.ic_home_fill
    ),
    FAVORITE(
        label = "찜",
        defaultIcon = R.drawable.ic_favorite_default,
        selectedIcon = R.drawable.ic_favorite_fill
    ),
    MY(
        label = "마이",
        defaultIcon = R.drawable.ic_user_default,
        selectedIcon = R.drawable.ic_user_fill
    ),
}

@Composable
fun MainRoute(
    user: UserModel?,
    onSearchClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    MainScreen(
        user = user,
        onSearchClick = onSearchClick,
        modifier = modifier.padding()
    )
}

@Composable
fun MainScreen(
    user: UserModel?,
    onSearchClick:() -> Unit,
    tab: MainTab = MainTab.HOME,
    modifier: Modifier = Modifier,
) {
    var selectedTab by remember { mutableStateOf(tab) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = PlaySoptTheme.colors.white,
        bottomBar = {
            MainScreenBottomBar(
                selectedTab = selectedTab,
                onTabClick = { tab ->
                    selectedTab = tab
                },
            )
        },
        topBar = {
            MainScreenTopBar(
                onSearchClick = onSearchClick,
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { innerPadding ->
        when (selectedTab) {
            MainTab.HOME -> MainHomeRoute(
                user = user,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            MainTab.FAVORITE -> MainFavoriteScreen( //Route변경 예정
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )

            MainTab.MY -> MainMyScreen( //Route 변경 예정
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainMyScreenPreview() {
    PlaySoptTheme {
        MainScreen(
            tab = MainTab.HOME,
            onSearchClick = {},
            modifier = Modifier.fillMaxSize(),
            user = UserModel(name = "김솝트", email = "abc@email.com", password = "123456"),
            )
    }
}

