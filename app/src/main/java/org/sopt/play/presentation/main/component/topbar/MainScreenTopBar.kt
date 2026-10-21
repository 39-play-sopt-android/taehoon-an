package org.sopt.play.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun MainScreenTopBar(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val titleStyle = PlaySoptTheme.typography.b28

    val titleColor = PlaySoptTheme.colors.black
    val searchColor = PlaySoptTheme.colors.black
    val backgroundColor = PlaySoptTheme.colors.white

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(start = 16.dp, end = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "PLAY SOPT",
            style = titleStyle,
            color = titleColor,
        )

        Box(
            modifier = Modifier.padding(10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_search_default),
                contentDescription = "검색",
                tint = searchColor,
                modifier = Modifier.size(24.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenTopBarPreview() {
    PlaySoptTheme {
        MainScreenTopBar(
            onSearchClick = {},
        )
    }
}
