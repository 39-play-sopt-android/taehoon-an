package org.sopt.play.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.data.model.UserModel
import org.sopt.play.data.model.compareToAccount
import org.sopt.play.presentation.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    private var registerdUser: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                AppNavHost(
                    onRegister = { user ->
                        registerdUser = user
                    },
                    onLogin = { user ->
                        val isValid = registerdUser
                            ?.compareToAccount(user.email, user.password)
                            ?: false

                        if (!isValid) {
                            Toast.makeText(
                                this@MainActivity,
                                "이메일 또는 비밀번호가 올바르지 않아요.",
                                Toast.LENGTH_SHORT,
                            ).show()
                        }
                        isValid
                    }
                )
            }
        }
    }
}
