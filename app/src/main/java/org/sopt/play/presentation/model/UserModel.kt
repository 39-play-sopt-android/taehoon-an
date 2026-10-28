package org.sopt.play.data.model

data class UserModel(
    val name: String? = null,
    val email: String,
    val password: String,
)

fun UserModel.compareToAccount(email : String, password : String): Boolean {
    return this.email == email && this.password == password
}
