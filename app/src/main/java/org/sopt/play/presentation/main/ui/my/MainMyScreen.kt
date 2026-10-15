package org.sopt.play.presentation.main.ui.my

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import org.sopt.play.R

@Composable
fun MainMyScreen(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(R.drawable.hachiware),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}
