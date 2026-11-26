package org.sopt.play.data.service

import org.sopt.play.data.dto.BaseResponse
import org.sopt.play.data.dto.search.request.PostCreateRequest
import org.sopt.play.data.dto.search.request.PostEditRequest
import org.sopt.play.data.dto.search.response.PostResponse
import org.sopt.play.data.dto.search.response.PostSliceResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchService {
    @GET("posts")
    suspend fun getPosts(
        @Query("keyword") keyword: String? = null,
        @Query("cursor") cursor: Long? = null,
        @Query("size") size: Int? = null
    ): BaseResponse<PostSliceResponse>

    @POST("posts")
    suspend fun createPost(
        @Header("Authorization") authorizationCode: String,
        @Body request: PostCreateRequest
    ) : BaseResponse<PostResponse>

    @PUT("posts/{postId}")
    suspend fun editPost(
        @Path ("postId") postId: Long,
        @Body request: PostEditRequest
    ) : BaseResponse<PostResponse>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path ("postId") postId: Long,
    ) : BaseResponse<String>
}
