package org.sopt.play.presentation.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.main.ui.MainScreen
import org.sopt.play.presentation.main.ui.MainTab

@Composable
fun MainFavoriteScreen(
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(R.drawable.chikawa),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun MainFavoriteScreenPreview() {
    PlaySoptTheme {
        MainScreen(
            tab = MainTab.FAVORITE,
            modifier = Modifier.fillMaxSize(),
            user = UserModel(name = "김솝트", email = "abc@email.com", password = "123456"),
            onSearchClick = {  },
        )
    }
}
