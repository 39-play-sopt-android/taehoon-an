package org.sopt.play.presentation.main.ui.home.component.chip

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

enum class MainChipType(val title: String) {
    ALL("전체"),
    TOPS("상의"),
    PANTS("팬츠"),
    DRESSES("원피스"),
    OUTERWEAR("아우터"),
    SKIRTS("스커트"),
    SHOES("슈즈"),
    BAGS("가방"),
    UNDERWEAR_SLEEPWEAR("이너웨어/잠옷"),
    PHONE_DECORATION("폰꾸미기"),
}

@Composable
fun MainChipRow(
    onChipClick: (MainChipType) -> Unit,
    selectedChip: MainChipType = MainChipType.ALL,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        MainChipType.entries.forEach { chip ->
            MainChip(
                text = chip.title,
                isSelected = selectedChip == chip,
                onClick = { onChipClick(chip) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainChipRowPreview() {
    PlaySoptTheme {
        var selectedChip by remember { mutableStateOf(MainChipType.ALL) }

        MainChipRow(
            selectedChip = selectedChip,
            onChipClick = { selectedChip = it },
        )
    }
}
