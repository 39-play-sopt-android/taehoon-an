package org.sopt.play.data.repository.login

interface LoginRepository {
    suspend fun issueAuthorizationCode(part: String): String
}
