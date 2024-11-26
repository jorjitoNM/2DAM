package com.example.apptareas.data.remote.datasource

import com.example.apptareas.data.remote.api_service.TodosService
import com.example.apptareas.data.remote.model.todos.toTodo
import javax.inject.Inject

class TodosDataSource @Inject constructor (
    private val todosService: TodosService,
) : BaseApiResponse() {
    suspend fun getUserTodos (userId : Int) =
        safeApiCall { todosService.getTodos(userId) }.map { todo -> todo.forEach { t -> t.toTodo() } }
}