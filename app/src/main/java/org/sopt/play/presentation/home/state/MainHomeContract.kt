package org.sopt.play.presentation.home.state

import org.sopt.play.R
import org.sopt.play.presentation.home.component.category.MainCategoryType
import org.sopt.play.presentation.home.component.menu.MainMenuType
import org.sopt.play.presentation.home.model.ProductModel

data class MainHomeUiState(
    val userName: String = "김솝트",
    val selectedMenu: MainMenuType = MainMenuType.HOME,
    val selectedCategory: MainCategoryType = MainCategoryType.ALL,
    val productList: List<ProductModel> = listOf(
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
        )
    )
)

sealed interface MainHomeAction {
    data class UserNameChanged(val name: String): MainHomeAction

    data class MenuClicked(val menu: MainMenuType): MainHomeAction

    data class CategoryClicked(val category: MainCategoryType): MainHomeAction

    data class FavoriteClicked(val product: ProductModel): MainHomeAction
}

sealed interface MainHomeEffect{
    data object ShowSearchScreen: MainHomeEffect
}
