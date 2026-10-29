package org.sopt.play.presentation.home.model

import androidx.annotation.DrawableRes

data class ProductModel(
    val storeName: String,
    val productName: String,
    val discountRate: Int,
    val price: String,
    val isFavorite: Boolean,
    @DrawableRes val imageRes: Int
)
