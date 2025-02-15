package com.example.musicappcompse.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.musicappcompse.data.local.dao.UsersDao
import com.example.musicappcompse.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersDao: UsersDao,
) {
    suspend fun registerUser(username: String, password: String): Result<Unit> {
        return try {
            val hashedPassword = password.hashCode().toString()
            val user = User(username = username, password = hashedPassword)
            usersDao.insertUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun login(username: String, password: String): Flow<Result<User?>> {
        val hashedPassword = password.hashCode().toString()
        return usersDao.login(username, hashedPassword)
            .map { user -> Result.success(user) }
            .catch { e -> emit(Result.failure(e)) }
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
