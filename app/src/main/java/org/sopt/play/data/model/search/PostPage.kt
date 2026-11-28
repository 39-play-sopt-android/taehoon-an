package org.sopt.play.data.model.search

data class PostPage(
    val posts: List<Post>,
    val nextCursor: Long?,
    val hasNext: Boolean,
)
