package org.sopt.play.presentation.register

import android.os.Bundle
import android.provider.Telephony.Carriers.PASSWORD
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import org.sopt.play.core.designsystem.theme.PlaySoptTheme
import org.sopt.play.presentation.register.ui.RegisterScreen

const val EMAIL = "email"
const val PASSWORD = "password"

class RegisterActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlaySoptTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = PlaySoptTheme.colors.white)
                            .padding(paddingValues = innerPadding),
                        onRegisterClick = { email, password ->
                            intent.putExtra(EMAIL, email)
                            intent.putExtra(PASSWORD, password)
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}
