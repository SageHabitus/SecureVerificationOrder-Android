package com.seongjae.secureorder.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class JwtDataStore(private val context: Context) {

    companion object {
        val JWT_TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    suspend fun saveJwtToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[JWT_TOKEN_KEY] = token
        }
    }

    val jwtToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[JWT_TOKEN_KEY]
        }

    suspend fun clearJwtToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(JWT_TOKEN_KEY)
        }
    }
}
