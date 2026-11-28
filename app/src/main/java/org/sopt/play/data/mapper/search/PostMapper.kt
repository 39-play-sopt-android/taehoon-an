package org.sopt.play.data.mapper.search

import org.sopt.play.data.dto.search.response.PostResponse
import org.sopt.play.data.dto.search.response.PostSliceResponse
import org.sopt.play.data.model.search.Post
import org.sopt.play.data.model.search.PostPage

fun PostResponse.toModel(): Post {
    return Post(
        id = id,
        title = title,
        content = content,
        author = author,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

fun PostSliceResponse.toModel(): PostPage {
    return PostPage(
        posts = posts.map { it.toModel() },
        nextCursor = nextCursor,
        hasNext = hasNext,
    )
}
