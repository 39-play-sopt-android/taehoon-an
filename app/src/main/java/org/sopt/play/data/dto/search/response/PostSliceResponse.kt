package org.sopt.play.data.dto.search.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostSliceResponse(
    @SerialName("posts")
    val posts: List<PostResponse>,
    @SerialName("nextCursor")
    val nextCursor: Long? = null,
    @SerialName("hasNext")
    val hasNext: Boolean
)
