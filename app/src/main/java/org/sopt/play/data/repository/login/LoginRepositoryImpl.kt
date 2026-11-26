package org.sopt.play.data.repository.login

import org.sopt.play.data.datasource.login.LoginDataSource
import org.sopt.play.data.dto.login.request.AuthorizationCodeRequest

class LoginRepositoryImpl(
    private val dataSource: LoginDataSource,
) : LoginRepository {

    override suspend fun issueAuthorizationCode(
        part: String,
    ): String {
        return dataSource.issueAuthorizationCode(
            request = AuthorizationCodeRequest(part = part),
        ).data.authorizationCode
    }
}
