package org.sopt.play.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.login.ui.LoginScreen
import org.sopt.play.presentation.main.MainActivity
import org.sopt.play.presentation.register.RegisterActivity

class LoginActivity : ComponentActivity() {
    private var registeredEmail: String? = null
    private var registeredPassword: String? = null
    private val registerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            registeredEmail =
                result.data?.getStringExtra("email") ?: return@registerForActivityResult
            registeredPassword =
                result.data?.getStringExtra("password") ?: return@registerForActivityResult
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = PlaySoptTheme.colors.white)
                            .padding(paddingValues = innerPadding),
                        onLoginClick = { email, password ->
                            if (email == registeredEmail && password == registeredPassword) {
                                val intent =
                                    Intent(this@LoginActivity, MainActivity::class.java).apply {
                                        flags =
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                    }
                                this.startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "이메일 또는 비밀번호가 올바르지 않아요.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }, // if문을 통해 분기처리 하기 (성공시 main, 실패시 toast)
                        onRegisterClick = {
                            registerLauncher.launch(
                                Intent(
                                    this@LoginActivity,
                                    RegisterActivity::class.java,
                                ),
                            )
                        },
                    )
                }
            }
        }
    }
}
