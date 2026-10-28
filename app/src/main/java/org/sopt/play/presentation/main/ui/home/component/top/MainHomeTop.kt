package org.sopt.play.presentation.main.ui.home.component.top

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.main.ui.home.component.banner.MainBannerRow
import org.sopt.play.presentation.main.ui.home.component.chip.MainChipRow
import org.sopt.play.presentation.main.ui.home.component.chip.MainChipType
import org.sopt.play.presentation.main.ui.home.component.tab.MainTabMenuRow
import org.sopt.play.presentation.main.ui.home.component.tab.MainTabType

@Composable
fun MainHomeTop(
    userName: String,
    selectedTab: MainTabType,
    selectedChip: MainChipType,
    onTabClick: (MainTabType) -> Unit,
    onChipClick: (MainChipType) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        MainTabMenuRow(
            selectedTab = selectedTab,
            onTabClick = onTabClick,
        )

        MainBannerRow()

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "${userName}님을 위한 추천 아이템",
            modifier = Modifier.padding(horizontal = 16.dp),
            color = PlaySoptTheme.colors.black,
            style = PlaySoptTheme.typography.m18,
        )

        Spacer(modifier = Modifier.height(12.dp))

        MainChipRow(
            selectedChip = selectedChip,
            onChipClick = onChipClick,
        )

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
private fun MainHomeTop() {
    PlaySoptTheme {
        MainHomeTop(
            userName = "김솝트",
            selectedTab = MainTabType.HOME,
            selectedChip = MainChipType.ALL,
            onTabClick = {},
            onChipClick = {},
        )
    }
}
