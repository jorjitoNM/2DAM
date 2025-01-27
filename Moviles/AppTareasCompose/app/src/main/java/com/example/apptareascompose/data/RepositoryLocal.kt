package com.example.apptareascompose.data

import com.example.apptareascompose.common.NetworkResult
import com.example.apptareascompose.data.local.UserDao
import com.example.apptareascompose.data.local.modelo.toUser
import com.example.apptareascompose.data.utils.Constantes
import com.example.apptareascompose.domain.model.User
import com.example.primeraapp.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryLocal @Inject constructor(
    private val userDao: UserDao,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) {

    fun saveUser(user : User): Result<Unit> {
        TODO()
    }

    fun getUser (user : User) = flow {
            emit(NetworkResult.Loading<Unit>())
            val result = userDao.getUser(user.username).map { u -> u?.toUser() }
            emitAll(result)
        }
            .catch { e ->
                emit(NetworkResult.Error<Unit>(e.message ?: Constantes.DATA_BASE_ERROR))
            }
            .flowOn(dispatcher)
}
