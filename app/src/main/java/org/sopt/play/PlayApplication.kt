package org.sopt.play

import android.app.Application
import org.sopt.play.core.local.login.LoginManager
import org.sopt.play.data.client.RetrofitClient
import org.sopt.play.data.datasource.login.LoginDataSource
import org.sopt.play.data.datasource.search.SearchDataSource
import org.sopt.play.data.repository.login.LoginRepository
import org.sopt.play.data.repository.login.LoginRepositoryImpl
import org.sopt.play.data.repository.search.SearchRepository
import org.sopt.play.data.repository.search.SearchRepositoryImpl

class PlayApplication : Application() {
    val loginManager: LoginManager by lazy {
        LoginManager(applicationContext)
    }

    private val loginDataSource by lazy {
        LoginDataSource(
            service = RetrofitClient.loginService
        )
    }

    private val searchDataSource by lazy {
        SearchDataSource(
            service = RetrofitClient.searchService
        )
    }

    val loginRepository: LoginRepository by lazy {
        LoginRepositoryImpl(
            dataSource = loginDataSource
        )
    }

    val searchRepository: SearchRepository by lazy {
        SearchRepositoryImpl(
            dataSource = searchDataSource,
            loginManager = loginManager
        )
    }
}
