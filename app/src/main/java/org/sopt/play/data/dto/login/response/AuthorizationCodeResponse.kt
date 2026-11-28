package org.sopt.play.data.dto.login.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthorizationCodeResponse(
    @SerialName("authorizationCode")
    val authorizationCode: String
)
