package org.sopt.play.data.datasource.search

import org.sopt.play.data.dto.BaseResponse
import org.sopt.play.data.dto.search.request.PostCreateRequest
import org.sopt.play.data.dto.search.request.PostEditRequest
import org.sopt.play.data.dto.search.response.PostResponse
import org.sopt.play.data.dto.search.response.PostSliceResponse
import org.sopt.play.data.service.SearchService

class SearchDataSource(
    private val service: SearchService,
) {
    suspend fun getPosts(
        keyword: String?,
        cursor: Long?,
        size: Int?,
    ): BaseResponse<PostSliceResponse> {
        return service.getPosts(
            keyword = keyword,
            cursor = cursor,
            size = size,
        )
    }

    suspend fun createPost(
        authorizationCode: String,
        request: PostCreateRequest,
    ): BaseResponse<PostResponse> {
        return service.createPost(
            authorizationCode = authorizationCode,
            request = request,
        )
    }

    suspend fun editPost(
        authorizationCode: String,
        postId: Long,
        request: PostEditRequest,
    ): BaseResponse<PostResponse> {
        return service.editPost(
            authorizationCode = authorizationCode,
            postId = postId,
            request = request,
        )
    }

    suspend fun deletePost(
        authorizationCode: String,
        postId: Long,
    ): BaseResponse<String> {
        return service.deletePost(
            authorizationCode = authorizationCode,
            postId = postId,
        )
    }
}
