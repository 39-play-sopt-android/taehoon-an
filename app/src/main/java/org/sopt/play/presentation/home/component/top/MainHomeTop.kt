package org.sopt.play.presentation.home.component.top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.home.component.category.MainCategoryType
import org.sopt.play.presentation.home.component.menu.MainMenuType

@Composable
fun MainHomeTop(
    userName: String,
    selectedTab: MainMenuType,
    selectedChip: MainCategoryType,
    onTabClick: (MainMenuType) -> Unit,
    onChipClick: (MainCategoryType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {



    }
}

@Preview
@Composable
private fun MainHomeTop() {
    PlaySoptTheme {
        MainHomeTop(
            userName = "김솝트",
            selectedTab = MainMenuType.HOME,
            selectedChip = MainCategoryType.ALL,
            onTabClick = {},
            onChipClick = {},
        )
    }
}
