package com.example.apptareas.data.remote.datasource

import com.example.apptareas.data.remote.NetworkResult
import com.example.apptareas.data.remote.api_service.TodosService
import com.example.apptareas.data.remote.model.todos.toTodo
import com.example.apptareas.domain.model.Todo
import javax.inject.Inject

class TodosDataSource @Inject constructor (
    private val todosService: TodosService,
) : BaseApiResponse() {
    suspend fun getUserTodos () : NetworkResult<List<Todo>> {
        return safeApiCall { todosService.getTodos() }.map { todo -> todo.map { t -> t.toTodo() } }
    }
}