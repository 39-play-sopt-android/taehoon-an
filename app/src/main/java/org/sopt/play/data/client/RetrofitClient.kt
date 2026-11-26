package org.sopt.play.data.client

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.sopt.play.BuildConfig
import org.sopt.play.data.service.LoginService
import org.sopt.play.data.service.SearchService
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitClient {
    private val baseUrl = BuildConfig.BASE_URL
    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val client = OkHttpClient.Builder()
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json".toMediaType(),
                ),
            )
            .build()
    }

    val searchService: SearchService by lazy {
        retrofit.create(SearchService::class.java)
    }

    val loginService: LoginService by lazy{
        retrofit.create(LoginService::class.java)
    }
}
