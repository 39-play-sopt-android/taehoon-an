package org.sopt.play.data.datasource.login

import org.sopt.play.data.dto.BaseResponse
import org.sopt.play.data.dto.login.request.AuthorizationCodeRequest
import org.sopt.play.data.dto.login.response.AuthorizationCodeResponse
import org.sopt.play.data.service.LoginService

class LoginDataSource(
    private val service: LoginService,
) {
    suspend fun issueAuthorizationCode(
        request: AuthorizationCodeRequest,
    ): BaseResponse<AuthorizationCodeResponse> {
        return service.issueAuthorizationCode(request)
    }
}
