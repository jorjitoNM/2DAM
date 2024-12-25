package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.TodosDataSource
import com.example.apptareas.di.IoDispatcher
import com.example.apptareas.domain.model.Todo
import com.example.apptareas.utilities.Constantes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TodosRepository @Inject constructor(
    private val todosDataSource: TodosDataSource,
    @IoDispatcher val dispatcher : CoroutineDispatcher,
) {
    fun getUserTodos () : Flow<NetworkResult<List<Todo>>> =
        flow {
            emit(NetworkResult.Loading())
            val result = todosDataSource.getUserTodos()
            emit(result)
        }
            .catch {
                e -> emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
            }
            .flowOn(dispatcher)
}