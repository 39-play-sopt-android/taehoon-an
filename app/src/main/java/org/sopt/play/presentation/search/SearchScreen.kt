package org.sopt.play.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
) {
    SearchScreen(modifier = modifier)
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "Search 화면입니다")
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    PlaySoptTheme {
        SearchScreen()
    }
}
