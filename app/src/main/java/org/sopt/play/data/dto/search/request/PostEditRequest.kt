package org.sopt.play.data.dto.search.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PostEditRequest(
    @SerialName("title")
    val title: String,
    @SerialName("content")
    val content: String
)
