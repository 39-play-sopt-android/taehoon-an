package org.sopt.play.presentation.main.component.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme

@Composable
fun MainBannerRow(
    imageList: List<Int> = listOf(
        R.drawable.img_banner1,
        R.drawable.img_banner2,
        R.drawable.img_banner3,
    ),
    modifier: Modifier = Modifier,
) {
    if (imageList.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .aspectRatio(2f / 1f)
                .background(PlaySoptTheme.colors.gray2),
        )
        return
    }

    val pagerState = rememberPagerState(
        initialPage = 0, //처음 표시할 이미지
        pageCount = { Int.MAX_VALUE }, //전체 pager의 크기
    )

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        pageSpacing = 6.dp,
    ) { page ->
        Image(
            painter = painterResource(imageList[page % imageList.size]), // 무한으로 list를 보여주기 위해서
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(328f / 195f),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainBannerRowPreview() {
    PlaySoptTheme {
        MainBannerRow()
    }
}
