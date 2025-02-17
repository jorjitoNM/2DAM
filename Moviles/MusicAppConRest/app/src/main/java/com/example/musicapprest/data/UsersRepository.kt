package com.example.musicapprest.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.musicapprest.data.remote.datasource.UsersDataSource
import com.example.musicapprest.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersDataSource: UsersDataSource,
) {
    suspend fun registerUser(username: String, password: String): Result<Unit> {
        return TODO()
    }

    fun login(username: String, password: String): Flow<Result<User?>> {
        return TODO()
    }
}


object PreferencesKeys {
    val USER_NAME = stringPreferencesKey("user_name")
}

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {

    val userName: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.USER_NAME].orEmpty()
        }

    suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.USER_NAME] = name
        }
    }
}
