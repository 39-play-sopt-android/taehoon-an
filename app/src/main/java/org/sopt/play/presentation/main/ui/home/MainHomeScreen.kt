package org.sopt.play.presentation.main.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.data.model.UserModel
import org.sopt.play.presentation.main.ui.home.component.productcard.MainScreenProductCard
import org.sopt.play.presentation.main.ui.home.component.top.MainHomeTop
import org.sopt.play.presentation.main.ui.home.model.ProductModel

@Composable
fun MainHomeScreen(
    user: UserModel?,
    modifier: Modifier = Modifier,
) {
    var selectedTab by rememberSaveable { mutableStateOf("홈") }
    var selectedChip by rememberSaveable { mutableStateOf("전체") }
    val userName = user?.name ?: "김솝트"

    var itemList by rememberSaveable {
        mutableStateOf(
            listOf(
                ProductModel(
                    storeName = "트렌디어패럴",
                    productName = "데일리 브이넥 니트 가디건",
                    discountRate = 36,
                    price = "28,800",
                    isFavorite = false,
                    imageRes = R.drawable.dummy1,
                ),
                ProductModel(
                    storeName = "슬로우앤드",
                    productName = "가을 베이직 코튼 셔츠",
                    discountRate = 50,
                    price = "26,820",
                    isFavorite = true,
                    imageRes = R.drawable.dummy2,
                ),
                ProductModel(
                    storeName = "어텀",
                    productName = "보들보들 슬림핏 골지 티셔츠",
                    discountRate = 10,
                    price = "17,100",
                    isFavorite = false,
                    imageRes = R.drawable.dummy3,
                ),
                ProductModel(
                    storeName = "모던데이",
                    productName = "핀턱 와이드 슬랙스",
                    discountRate = 25,
                    price = "32,250",
                    isFavorite = false,
                    imageRes = R.drawable.dummy1,
                ),
                ProductModel(
                    storeName = "디어룸",
                    productName = "루즈핏 스트라이프 셔츠",
                    discountRate = 40,
                    price = "35,200",
                    isFavorite = true,
                    imageRes = R.drawable.dummy2,
                ),
                ProductModel(
                    storeName = "온더무드",
                    productName = "베이직 라운드 긴팔 티셔츠",
                    discountRate = 15,
                    price = "18,700",
                    isFavorite = false,
                    imageRes = R.drawable.dummy3,
                ),
                ProductModel(
                    storeName = "데일리유",
                    productName = "포근한 오버핏 니트 가디건",
                    discountRate = 30,
                    price = "29,400",
                    isFavorite = false,
                    imageRes = R.drawable.dummy1,
                ),
            )
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 96.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            MainHomeTop(
                userName = userName,
                selectedTab = selectedTab,
                selectedChip = selectedChip,
                onTabClick = { selectedTab = it },
                onChipClick = { selectedChip = it },
            )
        }

        itemsIndexed(itemList) { index, item ->
            MainScreenProductCard(
                product = item,
                onFavoriteClick = {
                    itemList = itemList.mapIndexed { itemIndex, product ->
                        if (itemIndex == index) {
                            product.copy(isFavorite = !product.isFavorite)
                        } else {
                            product
                        }
                    }
                },
            )
        }
    }
}
