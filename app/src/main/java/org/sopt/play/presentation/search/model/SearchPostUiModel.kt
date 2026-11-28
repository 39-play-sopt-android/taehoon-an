package org.sopt.play.presentation.search.model

data class SearchPostUiModel(
    val postId: Int,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: String,
    val updateAt: String,
)
