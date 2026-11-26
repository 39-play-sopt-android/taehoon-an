package org.sopt.play.data.repository.search

import org.sopt.play.data.dto.search.request.PostCreateRequest
import org.sopt.play.data.dto.search.request.PostEditRequest
import org.sopt.play.data.model.search.Post
import org.sopt.play.data.model.search.PostPage

interface SearchRepository {
    suspend fun getPosts(
        keyword: String?,
        cursor: Long?,
        size: Int?,
    ): PostPage

    suspend fun createPost(
        request: PostCreateRequest
    ) : Post

    suspend fun editPost(
        postId: Long,
        request: PostEditRequest,
    ): Post

    suspend fun deletePost(
        postId: Long,
    )
}
