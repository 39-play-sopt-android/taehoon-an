package org.sopt.play.presentation.main.ui.home.component.chip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.dp
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun MainChipRow(
    selectedChip: String,
    onChipClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    chipList: List<String> = listOf(
        "전체",
        "상의",
        "팬츠",
        "원피스",
        "아우터",
        "스커트",
        "슈즈",
        "가방",
        "이너웨어/잠옷",
        "폰꾸미기"
    ),
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(chipList) { item ->
            MainChip(
                text = item,
                isSelected = selectedChip == item,
                onClick = { onChipClick(item) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainChipRowPreview() {
    PlaySoptTheme {
        var selectedChip by remember {
            mutableStateOf("전체")
        }

        MainChipRow(
            selectedChip = selectedChip,
            onChipClick = { selectedChip = it },
        )
    }
}
