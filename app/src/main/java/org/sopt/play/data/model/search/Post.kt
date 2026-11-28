package org.sopt.play.data.model.search

data class Post(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val createdAt: String,
    val updatedAt: String,
)
