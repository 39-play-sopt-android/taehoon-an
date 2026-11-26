package org.sopt.play.data.dto.login.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationCodeRequest(
    @SerialName("part")
    val part: String
)
