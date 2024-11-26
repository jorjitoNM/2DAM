package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.TodosDataSource
import javax.inject.Inject

class TodosRepository @Inject constructor(
    private val todosDataSource: TodosDataSource,
) {
    suspend fun getUserTodos (userId : Int) =
        todosDataSource.getUserTodos(userId)
}