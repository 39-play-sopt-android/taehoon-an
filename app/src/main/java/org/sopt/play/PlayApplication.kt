package org.sopt.play

import android.app.Application
import org.sopt.play.core.local.login.LoginManager

class PlayApplication : Application() {
    val loginManager: LoginManager by lazy {
        LoginManager(applicationContext)
    }
}
