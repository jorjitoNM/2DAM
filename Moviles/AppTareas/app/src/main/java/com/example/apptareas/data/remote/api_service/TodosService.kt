package com.example.apptareas.data.remote.api_service

import com.example.apptareas.domain.model.Todo
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface TodosService {

    @GET("todos/{id}")
    suspend fun getTodos (@Path("id") userId : Int) : List<Todo>

    @DELETE("todos/{id}")
    suspend fun deleteTodo (@Path("id") todoId : Int )

}