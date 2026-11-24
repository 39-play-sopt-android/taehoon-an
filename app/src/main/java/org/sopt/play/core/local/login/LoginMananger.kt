package org.sopt.play.core.local.login

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
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
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    suspend fun saveLogin(id: String, password: String) {
        dataStore.edit { preferences ->
            preferences[Keys.ID] = id
            preferences[Keys.PASSWORD] = password
            preferences[Keys.IS_LOGGED_IN] = false
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

    suspend fun login(id: String, password: String): Boolean {
        var success = false

        dataStore.edit { preferences ->
            success =
                preferences[Keys.ID] == id &&
                        preferences[Keys.PASSWORD] == password

            if (success) {
                preferences[Keys.IS_LOGGED_IN] = true
            }
        }

        return success
    }

    suspend fun isLoggedIn(): Boolean {
        val preferences = dataStore.data.first()

        return preferences[Keys.IS_LOGGED_IN] == true &&
                preferences[Keys.ID] != null &&
                preferences[Keys.PASSWORD] != null
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[Keys.IS_LOGGED_IN] = false
        }
    }
}

data class LoginCredentials(
    val id: String,
    val password: String,
)
