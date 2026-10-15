package org.sopt.play.presentation.main.ui.home.component.productcard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.play.R
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.core.util.noRippleClickable
import org.sopt.play.data.model.ProductModel

@Composable
fun MainScreenProductCard(
    product: ProductModel,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val storeNameStyle = PlaySoptTheme.typography.m14
    val productNameStyle = PlaySoptTheme.typography.r14
    val discountRateStyle = PlaySoptTheme.typography.m14
    val priceStyle = PlaySoptTheme.typography.m14

    val storeNameColor = PlaySoptTheme.colors.black
    val productNameColor = PlaySoptTheme.colors.black
    val discountRateColor = PlaySoptTheme.colors.red
    val priceColor = PlaySoptTheme.colors.black
    val favoriteColor = if (product.isFavorite) PlaySoptTheme.colors.red else PlaySoptTheme.colors.white

    val favoriteRes = if (product.isFavorite) {
        painterResource(R.drawable.ic_favorite_fill)
    } else {
        painterResource(R.drawable.ic_favorite_default)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Box {
            Image(
                painter = painterResource(product.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.8f),
            )

            Icon(
                painter = favoriteRes,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(24.dp)
                    .noRippleClickable { onFavoriteClick() },
                tint = favoriteColor
            )
        }

        Column(
            modifier = Modifier.padding(start = 6.dp, top = 12.dp, end = 6.dp, bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = product.storeName,
                color = storeNameColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = storeNameStyle
            )

            Text(
                text = product.productName,
                maxLines = 1,
                color = productNameColor,
                overflow = TextOverflow.Ellipsis,
                style = productNameStyle
            )

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "${product.discountRate}%",
                    color = discountRateColor,
                    style = discountRateStyle
                )

                Text(
                    text = product.price,
                    color = priceColor,
                    style = priceStyle
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenProductCardPreview() {
    PlaySoptTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                MainScreenProductCard(
                    product = ProductModel(
                        storeName = "스토어명 최대 한 줄",
                        productName = "상품명 최대 한 줄",
                        discountRate = 49,
                        price = "29,370",
                        isFavorite = false,
                        imageRes = R.drawable.dummy1,
                    ),
                    onFavoriteClick = {},
                    modifier = Modifier.weight(1f),
                )

                MainScreenProductCard(
                    product = ProductModel(
                        storeName = "스토어명 최대 한 줄",
                        productName = "상품명 최대 한 줄",
                        discountRate = 49,
                        price = "29,370",
                        isFavorite = true,
                        imageRes = R.drawable.dummy1,
                    ),
                    onFavoriteClick = {},
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}
