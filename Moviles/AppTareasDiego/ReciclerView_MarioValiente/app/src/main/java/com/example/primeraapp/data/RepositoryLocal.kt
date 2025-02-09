package com.example.primeraapp.data

import com.example.primeraapp.data.local.UserDao
import com.example.primeraapp.data.local.modelo.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryLocal @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun registerUser(username: String, password: String): Result<Unit> {
        return try {
            val hashedPassword = password.hashCode().toString()
            val user = UserEntity(username = username, password = hashedPassword)
            userDao.insertUser(user)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun validateUser(username: String, password: String): Flow<Result<UserEntity?>> {
        val hashedPassword = password.hashCode().toString()
        return userDao.validateUser(username, hashedPassword)
            .map { user -> Result.success(user) }
            .catch { e -> emit(Result.failure(e)) }
    }
}
