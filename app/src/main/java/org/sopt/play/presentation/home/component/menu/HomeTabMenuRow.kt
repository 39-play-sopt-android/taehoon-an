package org.sopt.play.presentation.home.component.menu

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

enum class HomeMenuType(val title: String) {
    DOUBLE_POINTS("2배적립"),
    HOME("홈"),
    SELECTED("셀렉티드"),
    BRAND("브랜드"),
    BEAUTY("뷰티"),
    LIFE("라이프"),
    RANKING("랭킹"),
}

@Composable
fun HomeTabMenuRow(
    selectedMenu: HomeMenuType,
    onMenuClick: (HomeMenuType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
    ) {
        HomeMenuType.entries.forEach { tab ->
            HomeTabMenu(
                text = tab.title,
                isSelected = selectedMenu == tab,
                onClick = { onMenuClick(tab) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeTabMenuRowPreview() {
    PlaySoptTheme {
        var selectedTab by remember { mutableStateOf(HomeMenuType.HOME) }

        HomeTabMenuRow(
            selectedMenu = selectedTab,
            onMenuClick = { selectedTab = it },
        )
    }
}
