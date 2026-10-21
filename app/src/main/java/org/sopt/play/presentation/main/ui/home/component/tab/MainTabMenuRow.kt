package org.sopt.play.presentation.main.ui.home.component.tab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun MainTabMenuRow(
    selectedTab: String,
    onTabClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    tabList: List<String> = listOf(
        "2배적립",
        "홈",
        "셀렉티드",
        "브랜드",
        "뷰티",
        "라이프",
        "랭킹"
    ),
) {
    LazyRow(
        modifier = modifier.fillMaxWidth()
    ) {
        items(tabList) { item ->
            MainTabMenu(
                text = item,
                isSelected = selectedTab == item,
                onClick = { onTabClick(item) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenChipPreview() {
    PlaySoptTheme {
        var selectedChip by remember {
            mutableStateOf("홈")
        }

        MainTabMenuRow(
            selectedTab = selectedChip,
            onTabClick = { selectedChip = it },
        )
    }
}
