package org.sopt.play.data.repository.search

import org.sopt.play.core.local.login.LoginManager
import org.sopt.play.data.datasource.search.SearchDataSource
import org.sopt.play.data.dto.search.request.PostCreateRequest
import org.sopt.play.data.dto.search.request.PostEditRequest
import org.sopt.play.data.mapper.search.toModel
import org.sopt.play.data.model.search.Post
import org.sopt.play.data.model.search.PostPage

class SearchRepositoryImpl(
    private val dataSource: SearchDataSource,
    private val loginManager: LoginManager
) : SearchRepository {

    override suspend fun getPosts(
        keyword: String?,
        cursor: Long?,
        size: Int?,
    ): PostPage =
        dataSource.getPosts(
            keyword = keyword,
            cursor = cursor,
            size = size,
        ).data.toModel()

    override suspend fun createPost(
        request: PostCreateRequest,
    ): Post {
        val authorizationCode = loginManager.getAuthorizationCode()
            ?: throw IllegalStateException("인증 코드가 없습니다.")

        return dataSource.createPost(
            authorizationCode = authorizationCode,
            request = request,
        ).data.toModel()
    }

    override suspend fun editPost(
        postId: Long,
        request: PostEditRequest,
    ): Post =
        dataSource.editPost(
            postId = postId,
            request = request,
        ).data.toModel()

    override suspend fun deletePost(postId: Long) {
        dataSource.deletePost(postId = postId)
    }
}
