package com.example.apptareas.data.remote.api_service

import com.example.apptareas.data.remote.model.todos.TodoRemote
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TodosService {

    @GET("todos/{id}")
    suspend fun getTodos (@Path("id") userId : Int) : List<TodoRemote>

    @DELETE("todos/{id}")
    suspend fun deleteTodo (@Path("id") todoId : Int )

}