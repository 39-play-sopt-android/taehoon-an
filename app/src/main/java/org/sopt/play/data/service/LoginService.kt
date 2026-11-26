package org.sopt.play.data.service

import org.sopt.play.data.dto.BaseResponse
import org.sopt.play.data.dto.login.request.AuthorizationCodeRequest
import org.sopt.play.data.dto.login.response.AuthorizationCodeResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("members/authorization-code")
    suspend fun issueAuthorizationCode(
        @Body request: AuthorizationCodeRequest,
    ): BaseResponse<AuthorizationCodeResponse>
}
