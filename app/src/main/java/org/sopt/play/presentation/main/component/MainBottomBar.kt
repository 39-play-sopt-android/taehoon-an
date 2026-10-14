package org.sopt.play.presentation.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable
import org.sopt.play.presentation.main.ui.MainTab

@Composable
fun MainBottomBar(
    onTabClick: (MainTab) -> Unit,
    modifier: Modifier = Modifier,
    selectedTab: MainTab = MainTab.HOME,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = PlaySoptTheme.colors.white)
            .navigationBarsPadding()
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = PlaySoptTheme.colors.gray2
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            MainTab.entries.forEach { tab ->
                val isSelected = selectedTab == tab

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .noRippleClickable {
                            onTabClick(tab)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(2.dp),
                    ) {
                        Image(
                            painter = painterResource(
                                id = if (isSelected) {
                                    tab.selectedIcon
                                } else {
                                    tab.defaultIcon
                                },
                            ),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                        )

                        Text(
                            text = tab.label,
                            color = PlaySoptTheme.colors.black,
                            style = PlaySoptTheme.typography.sb14
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MainBottomBarPreview() {
    PlaySoptTheme {
        MainBottomBar(
            selectedTab = MainTab.HOME,
            onTabClick = {},
        )
    }
}
