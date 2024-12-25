package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.UsersDataSource
import com.example.apptareas.di.IoDispatcher
import com.example.apptareas.domain.model.User
import com.example.apptareas.utilities.Constantes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepository @Inject constructor (
    private val usersDataSource: UsersDataSource,
    @IoDispatcher val dispatcher : CoroutineDispatcher,
) {
    fun getUsers () : Flow<NetworkResult<List<User>>> =
        flow {
            emit(NetworkResult.Loading())
            val result = usersDataSource.getUsers()
            emit(result)
        }
            .catch {
                e -> emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
            }
            .flowOn(dispatcher)

    fun getUser(userId: Int) : Flow<NetworkResult<User>> =
        flow {
            emit(NetworkResult.Loading())
            val result = usersDataSource.getUser(userId)
            emit(result)
        }
            .catch {
                e -> emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
            }
            .flowOn(dispatcher)
}