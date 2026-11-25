package org.sopt.play

import android.app.Application
import org.sopt.play.core.local.login.AccountManager

class PlayApplication : Application() {
    val accountManager: AccountManager by lazy {
        AccountManager(applicationContext)
    }
}
