package com.example.musicapprest.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.musicapprest.data.local.di.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {


    private val LOGIN_TOKEN = stringPreferencesKey("jwt_login")
    private val REFRESH_TOKEN = stringPreferencesKey("jwt_refresh")


    fun getLoginToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[LOGIN_TOKEN]
        }
    }

    suspend fun saveLoginToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[LOGIN_TOKEN] = token
        }
    }

    suspend fun deleteLoginToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(LOGIN_TOKEN)
        }
    }

    fun getRefreshToken(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }
    }

    suspend fun saveRefreshToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[REFRESH_TOKEN] = token
        }
    }
}
