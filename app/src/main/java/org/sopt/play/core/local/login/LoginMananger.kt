package org.sopt.play.core.local.login

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.loginDataStore by preferencesDataStore(
    name = "login_storage",
)

class LoginManager(context: Context) {
    private val dataStore = context.applicationContext.loginDataStore
    private object Keys {
        val ID = stringPreferencesKey("id")
        val PASSWORD = stringPreferencesKey("password")
    }

    suspend fun saveLogin(id: String, password: String) {
        dataStore.edit { preferences ->
            preferences[Keys.ID] = id
            preferences[Keys.PASSWORD] = password
        }
    }

    suspend fun getLogin(): LoginCredentials? {
        val preferences = dataStore.data.first()

        val id = preferences[Keys.ID] ?: return null
        val password = preferences[Keys.PASSWORD] ?: return null

        return LoginCredentials(
            id = id,
            password = password,
        )
    }

    suspend fun clearLogin() {
        dataStore.edit { preferences ->
            preferences.remove(Keys.ID)
            preferences.remove(Keys.PASSWORD)
        }
    }
}

data class LoginCredentials(
    val id: String,
    val password: String,
)
