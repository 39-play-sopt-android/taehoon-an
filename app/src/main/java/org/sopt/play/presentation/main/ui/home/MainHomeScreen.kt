package org.sopt.play.presentation.main.ui.home

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import org.sopt.play.R

@Composable
fun MainHomeScreen(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(R.drawable.chikawa),
        contentDescription = null,
        modifier = modifier,
    )
}
