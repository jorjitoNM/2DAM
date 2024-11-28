package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.TodosDataSource
import com.example.apptareas.domain.model.Todo
import javax.inject.Inject

class TodosRepository @Inject constructor(
    private val todosDataSource: TodosDataSource,
) {
    suspend fun getUserTodos () : NetworkResult<List<Todo>> {
        return todosDataSource.getUserTodos()
    }
}